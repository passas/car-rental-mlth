package org.acme.db.psql.repository.rental;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.rental.RentalEntity;

@ApplicationScoped
public class RentalRepository implements PanacheRepositoryBase<RentalEntity, Long>
{
    public boolean isPresent(RentalEntity entity)
    {
        long n = count("id = ?1", entity.getId());
        return n > 0;
    }

    public boolean isStarted(RentalEntity entity)
    {
        long n = count("id = ?1 and startDate is not NULL", entity.getId());
        return n > 0;
    }

    public boolean isFinished(RentalEntity entity)
    {
        long n = count("id = ?1 and endDate is not NULL", entity.getId());
        return n > 0;
    }
}
