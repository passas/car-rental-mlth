package org.acme.api.rest.dto.inventory.mapper;

import org.acme.api.rest.dto.inventory.BrandDTO;
import org.acme.app.model.car.BrandModel;

public class BrandDTOMapper
{
    public static BrandDTO fromModel(BrandModel brandModel)
    {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brandModel.getId());
        brandDTO.setName(brandModel.getName());
        return brandDTO;
    }
}
