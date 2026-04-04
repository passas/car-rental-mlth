package org.acme.db.psql.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.car.FuelEntity;

@ApplicationScoped
public class FuelRepository implements PanacheRepositoryBase<FuelEntity, Short>
{
    public FuelEntity findByName(String name)
    {
        return this.find("name", name).firstResult();
    }
}
