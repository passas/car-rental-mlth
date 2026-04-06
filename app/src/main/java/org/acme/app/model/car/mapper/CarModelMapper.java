package org.acme.app.model.car.mapper;

import org.acme.api.rest.dto.inventory.CarDTO;
import org.acme.app.model.car.CarModel;
import org.acme.db.psql.entity.car.CarEntity;

public class CarModelMapper
{
    public static CarModel fromDto(CarDTO dto)
    {
        CarModel model = new CarModel();
        model.setId(dto.getId());
        model.setLicensePlate(dto.getLicensePlate());
        model.setTank(dto.getTank());
        model.setDistance(dto.getDistance());
        if (dto.getBrandModelDTO() != null)
            model.setBrandModelModel(BrandModelModelMapper.fromDto(dto.getBrandModelDTO()));
        return model;
    }

    public static CarModel fromEntity(CarEntity entity)
    {
        CarModel model = new CarModel();
        model.setId(entity.getId());
        model.setLicensePlate(entity.getLicensePlate());
        model.setTank(entity.getTank());
        model.setDistance(entity.getDistance());
        if (entity.getBrandModelEntity() != null)
            model.setBrandModelModel(BrandModelModelMapper.fromEntity(entity.getBrandModelEntity()));
        return model;
    }
}
