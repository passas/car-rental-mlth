package org.acme.db.psql.repository.user;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.user.RoleEntity;

@ApplicationScoped
public class RoleRepository implements PanacheRepositoryBase<RoleEntity, Short>
{
    public RoleEntity findByName(String name)
    {
        return this.find("name", name).firstResult();
    }
}
