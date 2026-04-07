package org.acme.api.rest.dto.rental.mapper;

import org.acme.api.rest.dto.rental.RentalDTO;
import org.acme.app.model.rental.RentalModel;

public class RentalDTOMapper
{
    public static RentalDTO fromModel(RentalModel model)
    {
        RentalDTO dto = new RentalDTO();
        dto.setId(model.getId());
        dto.setReservationId(model.getReservationId());
        dto.setStartDate(model.getStartDate());
        dto.setEndDate(model.getEndDate());
        dto.setAmount(model.getAmount());
        return dto;
    }
}
