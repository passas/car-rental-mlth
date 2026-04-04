package org.acme.app.model.car.mapper;

import org.acme.api.rest.dto.FuelDTO;
import org.acme.app.model.car.FuelModel;
import org.acme.db.psql.entity.car.FuelEntity;

public class FuelModelMapper
{
    public static FuelModel fromDto(FuelDTO dto)
    {
        FuelModel model = new FuelModel();
        model.setId(dto.getId());
        model.setName(dto.getName());
        return model;
    }

    public static FuelModel fromEntity(FuelEntity entity)
    {
        FuelModel model = new FuelModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        return model;
    }
}
