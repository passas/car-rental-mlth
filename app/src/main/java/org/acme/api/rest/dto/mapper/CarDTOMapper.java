package org.acme.api.rest.dto.mapper;

import org.acme.api.rest.dto.CarDTO;
import org.acme.app.model.car.CarModel;

public class CarDTOMapper
{
    public static CarDTO fromModel(CarModel carModel)
    {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(carModel.getId());
        carDTO.setLicensePLate(carModel.getLicensePlate());
        carDTO.setTank(carModel.getTank());
        carDTO.setDistance(carModel.getDistance());
        if (carModel.getBrandModelModel() != null)
            carDTO.setBrandModelDTO(BrandModelDTOMapper.fromModel(carModel.getBrandModelModel()));
        return carDTO;
    }
}
