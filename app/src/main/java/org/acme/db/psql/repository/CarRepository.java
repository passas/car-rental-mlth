package org.acme.db.psql.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.car.CarEntity;

@ApplicationScoped
public class CarRepository implements PanacheRepositoryBase<CarEntity, Long>
{
    public CarEntity findByLicensePlate(String licensePlate)
    {
        return this.find("licensePlate", licensePlate).firstResult();
    }

    public long countById(Long id)
    {
        return this.count("id = ?1", id);
    }

    public long countByLicensePlate(String licensePlate)
    {
        return this.count("licensePlate = ?1", licensePlate);
    }
}
