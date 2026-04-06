package org.acme.app.model.car.mapper;

import org.acme.api.rest.dto.inventory.BrandDTO;
import org.acme.app.model.car.BrandModel;
import org.acme.db.psql.entity.car.BrandEntity;

public class BrandModelMapper
{
    public static BrandModel fromDto(BrandDTO brandDto)
    {
        BrandModel brandModel = new BrandModel();
        brandModel.setId(brandDto.getId());
        brandModel.setName(brandDto.getName());
        return brandModel;
    }

    public static BrandModel fromEntity(BrandEntity brandEntity)
    {
        BrandModel brandModel = new BrandModel();
        brandModel.setId(brandEntity.getId());
        brandModel.setName(brandEntity.getName());
        return brandModel;
    }
}
