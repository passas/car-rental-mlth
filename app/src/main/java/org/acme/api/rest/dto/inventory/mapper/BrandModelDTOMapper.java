package org.acme.api.rest.dto.inventory.mapper;

import org.acme.api.rest.dto.inventory.BrandModelDTO;
import org.acme.app.model.car.BrandModelModel;

public class BrandModelDTOMapper
{
    public static BrandModelDTO fromModel(BrandModelModel model)
    {
        BrandModelDTO dto = new BrandModelDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setVariant(model.getVariant());
        if (model.getBrandModel() != null)
            dto.setBrandDTO(BrandDTOMapper.fromModel(model.getBrandModel()));
        if (model.getFuelModel() != null)
            dto.setFuelDTO(FuelDTOMapper.fromModel(model.getFuelModel()));
        return dto;
    }
}
