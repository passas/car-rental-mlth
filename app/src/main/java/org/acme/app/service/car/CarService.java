package org.acme.app.service.car;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.app.model.car.CarModel;
import org.acme.app.model.car.mapper.CarModelMapper;
import org.acme.db.psql.repository.BrandModelRepository;
import org.acme.db.psql.repository.BrandRepository;
import org.acme.db.psql.repository.CarRepository;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CarService
{
    private Logger log;
    private BrandRepository brandRepository;
    private BrandModelRepository brandModelRepository;
    private CarRepository carRepository;

    public CarService(BrandRepository brandRepository, BrandModelRepository brandModelRepository, CarRepository carRepository)
    {
        this.log = Logger.getLogger(CarService.class.getSimpleName());
        this.brandRepository = brandRepository;
        this.brandModelRepository = brandModelRepository;
        this.carRepository = carRepository;
    }

    /**
     *
     * @return All the system's car.
     */
    @Transactional
    public List<CarModel> getAll()
    {
        return this.carRepository.findAll().stream().map(CarModelMapper::fromEntity).collect(Collectors.toList());
    }
}