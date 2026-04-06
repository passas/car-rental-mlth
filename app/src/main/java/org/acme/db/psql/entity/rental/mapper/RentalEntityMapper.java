package org.acme.db.psql.entity.rental.mapper;


import org.acme.app.model.rental.RentalModel;
import org.acme.db.psql.entity.rental.RentalEntity;
import org.acme.db.psql.entity.reservation.ReservationEntity;

public class RentalEntityMapper
{
    public static RentalEntity fromModel(RentalModel model)
    {
        RentalEntity entity = new RentalEntity();
        entity.setId(model.getId());
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setId(model.getReservationId());
        entity.setReservationEntity(reservationEntity);
        entity.setStartDate(model.getDateStart());
        entity.setEndDate(model.getDateEnd());
        entity.setAmount(model.getAmount());
        return entity;
    }
}
