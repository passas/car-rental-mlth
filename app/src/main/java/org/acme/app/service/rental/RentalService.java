package org.acme.app.service.rental;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.app.model.rental.RentalModel;
import org.acme.app.model.rental.mapper.RentalModelMapper;
import org.acme.db.psql.entity.rental.RentalEntity;
import org.acme.db.psql.entity.rental.mapper.RentalEntityMapper;
import org.acme.db.psql.repository.rental.RentalRepository;
import org.acme.db.psql.repository.rental.exception.RentalRepositoryConstraintEndDateCheckException;
import org.acme.db.psql.repository.rental.exception.RentalRepositoryConstraintReservationForeignKeyException;
import org.acme.db.psql.repository.rental.exception.RentalRepositoryConstraintReservationUniqueException;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

import java.math.BigDecimal;

@ApplicationScoped
public class RentalService
{
    private Logger log;
    private RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository)
    {
        this.log = Logger.getLogger(RentalService.class.getSimpleName());
        this.rentalRepository = rentalRepository;
    }

    @Transactional
    public RentalModel createRental(RentalModel model) throws RentalRepositoryConstraintReservationForeignKeyException, RentalRepositoryConstraintReservationUniqueException, RentalRepositoryConstraintEndDateCheckException
    {
        this.log.infof("Creating a new rental: %s", model);
        RentalEntity rentalEntity = RentalEntityMapper.fromModel(model);
        try
        {
            this.rentalRepository.persist(rentalEntity);
            this.rentalRepository.flush();
        }
        catch (ConstraintViolationException e)
        {
            String constraint = e.getConstraintName();
            if("fk_rental_reservation".equals(constraint))
            {
                this.log.error("There's no reservation with the given id");
                throw new RentalRepositoryConstraintReservationForeignKeyException();
            }
            else if("uq_rental_reservation".equals(constraint))
            {
                this.log.error("Rental already registered");
                throw new RentalRepositoryConstraintReservationUniqueException();
            }
        }
        this.log.infof("New rental created: %s", rentalEntity);
        return RentalModelMapper.fromEntity(rentalEntity);
    }

    @Transactional
    public boolean isRentalStarted(RentalModel model)
    {
        return this.rentalRepository.isStarted(RentalEntityMapper.fromModel(model));
    }

    @Transactional
    public boolean isManageableRental(RentalModel model)
    {
        RentalEntity entity = RentalEntityMapper.fromModel(model);
        boolean isPresent = this.rentalRepository.isPresent(entity);
        boolean isStarted = this.rentalRepository.isStarted(entity);
        boolean isFinished = this.rentalRepository.isFinished(entity);
        boolean isManageable = isPresent && isStarted && !isFinished;
        return isManageable;
    }

    @Transactional
    public RentalModel endRental(RentalModel model) throws RentalRepositoryConstraintEndDateCheckException
    {
        RentalEntity rentalEntity = this.rentalRepository.findById(model.getId());
        rentalEntity.setEndDate(model.getEndDate());
        rentalEntity.setAmount(new BigDecimal("1001"));
        try
        {
            this.rentalRepository.flush();
        }
        catch (ConstraintViolationException e)
        {
            String s = e.getConstraintName();
            if ("date_end_after_date_start".equals(s))
            {
                this.log.error("End date can't be prior the starting date!");
                throw new RentalRepositoryConstraintEndDateCheckException();
            }
        }
        return RentalModelMapper.fromEntity(rentalEntity);
    }
}
