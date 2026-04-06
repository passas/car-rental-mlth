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
        model.setDateStart(dto.getDateStart());
        model.setDateEnd(dto.getDateEnd());
        model.setAmount(dto.getAmount());
        return model;
    }

    public static RentalModel fromEntity(RentalEntity entity)
    {
        RentalModel model = new RentalModel();
        model.setId(entity.getId());
        model.setReservationId(entity.getReservationEntity().getId());
        model.setDateStart(entity.getStartDate());
        model.setDateEnd(entity.getEndDate());
        model.setAmount(entity.getAmount());
        return model;
    }
}
