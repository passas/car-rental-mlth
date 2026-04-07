package org.acme.app.model.rental.mapper;


import org.acme.api.rest.dto.rental.RentalDTO;
import org.acme.app.model.rental.RentalModel;
import org.acme.db.psql.entity.rental.RentalEntity;

public class RentalModelMapper
{
    public static RentalModel fromDTO(RentalDTO dto)
    {
        RentalModel model = new RentalModel();
        model.setId(dto.getId());
        model.setReservationId(dto.getReservationId());
        model.setStartDate(dto.getStartDate());
        model.setEndDate(dto.getEndDate());
        model.setAmount(dto.getAmount());
        return model;
    }

    public static RentalModel fromEntity(RentalEntity entity)
    {
        RentalModel model = new RentalModel();
        model.setId(entity.getId());
        model.setReservationId(entity.getReservationEntity().getId());
        model.setStartDate(entity.getStartDate());
        model.setEndDate(entity.getEndDate());
        model.setAmount(entity.getAmount());
        return model;
    }
}
