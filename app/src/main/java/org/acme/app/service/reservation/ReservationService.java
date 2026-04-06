package org.acme.app.service.reservation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.app.model.car.CarModel;
import org.acme.app.model.car.mapper.CarModelMapper;
import org.acme.app.model.reservation.ReservationModel;
import org.acme.app.model.reservation.mapper.ReservationModelMapper;
import org.acme.db.psql.entity.car.CarEntity;
import org.acme.db.psql.entity.reservation.ReservationEntity;
import org.acme.db.psql.entity.reservation.mapper.ReservationEntityMapper;
import org.acme.db.psql.repository.car.CarRepository;
import org.acme.db.psql.repository.reservation.ReservationRepository;
import org.acme.db.psql.repository.reservation.exception.ReservationRepositoryConstraintCarForeignKeyException;
import org.acme.db.psql.repository.reservation.exception.ReservationRepositoryConstraintFromDateToDateCheckException;
import org.acme.db.psql.repository.reservation.exception.ReservationRepositoryConstraintPeriodOverlapExcludeException;
import org.acme.db.psql.repository.reservation.exception.ReservationRepositoryConstraintUserForeignKeyException;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReservationService
{
    private Logger log;
    private CarRepository carRepository;
    private ReservationRepository reservationRepository;

    public ReservationService(CarRepository carRepository, ReservationRepository reservationRepository)
    {
        this.log = Logger.getLogger(ReservationService.class.getSimpleName());
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public Set<CarModel> checkAvailableCars(LocalDate from, LocalDate to)
    {
        Set<CarEntity> availability = this.carRepository.findAll().stream().collect(Collectors.toSet());
        Instant fromInstant = from.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant toInstant = to.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();
        List<ReservationEntity> inavailability = this.reservationRepository.findOverlap(fromInstant, toInstant);
        Set<Long> ids = inavailability.stream().map(e -> e.getCarEntity().getId()).collect(Collectors.toSet());
        if (!inavailability.isEmpty())
            availability.removeIf(car -> ids.contains(car.getId()));
        return availability.stream().map(CarModelMapper::fromEntity).collect(Collectors.toSet());
    }

    @Transactional
    public ReservationModel createReservation(ReservationModel model) throws ReservationRepositoryConstraintUserForeignKeyException, ReservationRepositoryConstraintCarForeignKeyException, ReservationRepositoryConstraintPeriodOverlapExcludeException, ReservationRepositoryConstraintFromDateToDateCheckException
    {
        ReservationEntity entity = ReservationEntityMapper.fromModel(model);
        this.log.infof("Creating a new reservation:\n %s", entity);
        try
        {
            this.reservationRepository.persist(entity);
            this.reservationRepository.flush();
        }
        catch (ConstraintViolationException e)
        {
            String c = e.getConstraintName();
            if("fk_reservation_user_user_id".equals(c))
            {
                this.log.error("User id not found!");
                throw new ReservationRepositoryConstraintUserForeignKeyException();
            }
            else if("fk_reservation_car_car_id".equals(c))
            {
                this.log.error("Car id not found!");
                throw new ReservationRepositoryConstraintCarForeignKeyException();
            }
            else if ("chk_from_date_to_date".equals(c))
            {
                this.log.error("Wrong reservation period date.");
                throw new ReservationRepositoryConstraintFromDateToDateCheckException();
            }
            else if ("23P01".equals(e.getSQLState())) // exc_rental_period_overlap
            {
                this.log.error("There's already a car reservation for that period!");
                throw new ReservationRepositoryConstraintPeriodOverlapExcludeException();
            }
        }
        ReservationModel saved = ReservationModelMapper.fromEntity(entity);
        this.log.infof("Reservation created:\n %s", saved);
        return saved;
    }
}
