package org.acme.db.psql.repository.payment;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.payment.PaymentEntity;

@ApplicationScoped
public class PaymentRepository implements PanacheRepositoryBase<PaymentEntity, Long>
{
    public boolean isCreated(PaymentEntity entity)
    {
        long n = count("id = ?1 and dateCreation is not NULL", entity.getId());
        return n > 0;
    }

    public boolean isPayed(PaymentEntity entity)
    {
        long n = count("id = ?1 and datePay is not NULL", entity.getId());
        return n > 0;
    }
}
