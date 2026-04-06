package org.acme.api.rest.dto.inventory.mapper;

import org.acme.api.rest.dto.inventory.FuelDTO;
import org.acme.app.model.car.FuelModel;

public class FuelDTOMapper
{
    public static FuelDTO fromModel(FuelModel model)
    {
        FuelDTO dto = new FuelDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        return dto;
    }
}
