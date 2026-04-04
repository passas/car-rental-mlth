package org.acme.db.psql.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.user.UserEntity;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, Long>
{
    public UserEntity findByUsername(String username)
    {
        return this.find("username", username).firstResult();
    }
}
