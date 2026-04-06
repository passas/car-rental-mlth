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
            if("uq_rental_reservation".equals(constraint))
            {
                this.log.error("Rental already registered");
                throw new RentalRepositoryConstraintReservationUniqueException();
            }
        }
        this.log.infof("New rental created: %s", rentalEntity);
        return RentalModelMapper.fromEntity(rentalEntity);
    }

//    @Transactional
//    public boolean rentalHasStarted(RentalModel model)
//    {
//        return this.rentalRepository.isStarted(RentalEntityMapper.fromModel(model));
//    }

//    @Transactional
//    public boolean isManageableRental(RentalModel model)
//    {
//        this.log.trace("isManageableRental@in");
//        this.log.debugf("isManageableRental@in %s", model);
//        RentalEntity entity = RentalEntityMapper.fromModel(model);
//        this.log.tracef("isManageableRental@in %s", entity);
//        boolean isStarted = this.rentalRepository.isStarted(entity);
//        boolean isFinished = this.rentalRepository.isFinished(entity);
//        boolean isManageable = isStarted && !isFinished;
//        this.log.tracef("isManageableRental@isStarted %s", isStarted);
//        this.log.tracef("isManageableRentalt@isFinished %s", isFinished);
//        this.log.debugf("isManageableRental@out %s", isManageable);
//        this.log.trace("isManageableRental@out");
//        return isManageable;
//    }
//

//
//    @Transactional
//    public RentalModel endRental(RentalModel model) throws RentalRepositoryConstraintDateEndAfterDateStartException
//    {
//        this.log.trace("endRental@in");
//        this.log.debugf("endRental@in %s", model);
//        RentalEntity rentalEntity = RentalEntityMapper.fromModel(model);
//        this.log.tracef("endRental %s", rentalEntity);
//        rentalEntity = this.rentalRepository.findById(rentalEntity.getId());
//        rentalEntity.setDateEnd(Instant.from(LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()));
//        rentalEntity.setAmount(new BigDecimal("1001"));
//        this.log.debugf("endRental %s", rentalEntity);
//        try
//        {
//            this.rentalRepository.persist(rentalEntity);
//            this.rentalRepository.flush();
//        } catch (ConstraintViolationException e)
//        {
//            String s = e.getConstraintName();
//            if ("date_end_after_date_start".equals(s))
//            {
//                this.log.error("endRental@out DateEndPriorDateStart!");
//                throw new RentalRepositoryConstraintDateEndAfterDateStartException();
//            }
//        }
//        RentalModel rental = RentalModelMapper.fromEntity(rentalEntity);
//        this.log.debugf("endRental@out %s", rental);
//        this.log.trace("endRental@out");
//        return rental;
//    }
//
//    @Transactional
//    public void ping(RentalModel rentalModel) throws RentalServicePingRentalDoesNotExistException, RentalServicePingRentalDoesNotStartedException, RentalServicePingRentalDoesNotFinishedException
//    {
//        this.log.trace("ping@in");
//        this.log.debugf("ping@in %s", rentalModel);
//        RentalEntity entity = RentalEntityMapper.fromModel(rentalModel);
//        this.log.tracef("ping %s", entity);
//        if(this.rentalRepository.isPresent(entity) == false)
//        {
//            this.log.error("ping@out RentalDoesNotExist!");
//            throw new RentalServicePingRentalDoesNotExistException();
//        }
//        else if (this.rentalRepository.isStarted(entity) == false)
//        {
//            this.log.error("ping@out RentalDoesNotStarted!");
//            throw new RentalServicePingRentalDoesNotStartedException();
//        }
//        else if (this.rentalRepository.isFinished(entity) == false)
//        {
//            this.log.error("ping@out RentalDoesNotFinished!");
//            throw new RentalServicePingRentalDoesNotFinishedException();
//        }
//        this.log.trace("ping@out");
//    }
//
//    @Transactional
//    public RentalModel getRental(RentalModel model)
//    {
//        this.log.trace("getRental@in");
//        this.log.debugf("getRental@in %s", model);
//        RentalEntity entity = this.rentalRepository.findById(model.getId());
//        this.log.tracef("getRental %s", entity);
//        RentalModel rentalModel = RentalModelMapper.fromEntity(entity);
//        this.log.debugf("getRental@out %s", rentalModel);
//        this.log.trace("getRental@out");
//        return rentalModel;
//    }
}
