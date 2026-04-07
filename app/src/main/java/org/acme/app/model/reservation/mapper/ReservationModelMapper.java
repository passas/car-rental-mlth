package org.acme.app.model.reservation.mapper;


import org.acme.api.rest.dto.reservation.ReservationDTO;
import org.acme.app.model.reservation.ReservationModel;
import org.acme.db.psql.entity.reservation.ReservationEntity;

public class ReservationModelMapper
{
    public static ReservationModel fromDTO(ReservationDTO dto)
    {
        ReservationModel model = new ReservationModel();
        model.setId(dto.getId());
        model.setCarId(dto.getCarId());
        model.setUserId(dto.getUserId());
        model.setFromDate(dto.getFromDate());
        model.setToDate(dto.getToDate());
        return model;
    }

    public static ReservationModel fromEntity(ReservationEntity entity)
    {
        ReservationModel model = new ReservationModel();
        model.setId(entity.getId());
        model.setCarId(entity.getCarEntity().getId());
        model.setUserId(entity.getUserEntity().getId());
        model.setFromDate(entity.getDateFrom());
        model.setToDate(entity.getDateTo());
        return model;
    }
}
