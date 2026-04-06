package org.acme.app.model.car.mapper;

import org.acme.api.rest.dto.inventory.BrandModelDTO;
import org.acme.app.model.car.BrandModelModel;
import org.acme.db.psql.entity.car.BrandModelEntity;

public class BrandModelModelMapper
{
    public static BrandModelModel fromDto(BrandModelDTO dto)
    {
        BrandModelModel model = new BrandModelModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setVariant(dto.getVariant());
        if (dto.getBrandDTO() != null)
            model.setBrandModel(BrandModelMapper.fromDto(dto.getBrandDTO()));
        if (dto.getFuelDTO() != null)
            model.setFuelModel(FuelModelMapper.fromDto(dto.getFuelDTO()));
        return model;
    }

    public static BrandModelModel fromEntity(BrandModelEntity entity)
    {
        BrandModelModel model = new BrandModelModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setVariant(entity.getVariant());
        if (entity.getBrandEntity() != null)
            model.setBrandModel(BrandModelMapper.fromEntity(entity.getBrandEntity()));
        if (entity.getFuelEntity() != null)
            model.setFuelModel(FuelModelMapper.fromEntity(entity.getFuelEntity()));
        return model;
    }
}
