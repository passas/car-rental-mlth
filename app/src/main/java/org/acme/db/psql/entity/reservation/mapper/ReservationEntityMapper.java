package org.acme.db.psql.entity.reservation.mapper;


import org.acme.app.model.reservation.ReservationModel;
import org.acme.db.psql.entity.car.CarEntity;
import org.acme.db.psql.entity.reservation.ReservationEntity;
import org.acme.db.psql.entity.user.UserEntity;

public class ReservationEntityMapper
{
    public static ReservationEntity fromModel(ReservationModel model)
    {
        ReservationEntity entity = new ReservationEntity();
        entity.setId(model.getId());
        CarEntity car = new CarEntity();
        car.setId(model.getCarId());
        entity.setCarEntity(car);
        UserEntity user = new UserEntity();
        user.setId(model.getUserId());
        entity.setUserEntity(user);
        entity.setDateFrom(model.getDateFrom());
        entity.setDateTo(model.getDateTo());
        return entity;
    }
}
