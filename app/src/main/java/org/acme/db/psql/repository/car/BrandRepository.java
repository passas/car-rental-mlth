package org.acme.db.psql.repository.car;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.car.BrandEntity;

@ApplicationScoped
public class BrandRepository implements PanacheRepositoryBase<BrandEntity, Short>
{
    public BrandEntity findByName(String name)
    {
        return this.find("name", name).firstResult();
    }
}
