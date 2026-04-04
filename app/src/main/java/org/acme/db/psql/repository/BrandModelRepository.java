package org.acme.db.psql.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.car.BrandModelEntity;

@ApplicationScoped
public class BrandModelRepository implements PanacheRepositoryBase<BrandModelEntity, Integer>
{
    public BrandModelEntity findByName(String name)
    {
        return this.find("name", name).firstResult();
    }
}
