package org.acme.app.service.car;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.app.model.car.CarModel;
import org.acme.app.model.car.mapper.CarModelMapper;
import org.acme.db.psql.repository.car.BrandModelRepository;
import org.acme.db.psql.repository.car.BrandRepository;
import org.acme.db.psql.repository.car.CarRepository;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CarService
{
    private Logger log;
    private CarRepository carRepository;

    public CarService(CarRepository carRepository)
    {
        this.log = Logger.getLogger(CarService.class.getSimpleName());
        this.carRepository = carRepository;
    }

    @Transactional
    public List<CarModel> getAll()
    {
        return this.carRepository.findAll().stream().map(CarModelMapper::fromEntity).collect(Collectors.toList());
    }
}