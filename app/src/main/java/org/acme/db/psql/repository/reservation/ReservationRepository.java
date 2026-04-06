package org.acme.db.psql.repository.reservation;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.reservation.ReservationEntity;

import java.time.Instant;
import java.util.List;

@ApplicationScoped
public class ReservationRepository implements PanacheRepositoryBase<ReservationEntity, Long>
{
    public long countById(Long id)
    {
        return this.count("id = ?1", id);
    }

    public List<ReservationEntity> findOverlap(Instant from, Instant to)
    {
        return find("dateFrom < ?2 and dateTo > ?1", from, to).list();
    }

    public boolean isOverlap(Instant from, Instant to, Long carId)
    {
        return count("carId = ?3 and dateFrom < ?2 and dateTo > ?1", from, to, carId) > 0;
//        return count("carId = :carId and dateFrom < :to and dateTo > :from",
//                Parameters.with("carId", carId)
//                        .and("from", from)
//                        .and("to", to)) > 0;
    }
}
