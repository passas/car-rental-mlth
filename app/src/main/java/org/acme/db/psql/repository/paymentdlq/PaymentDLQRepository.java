package org.acme.db.psql.repository.paymentdlq;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.psql.entity.paymentdlq.PaymentDLQEntity;

@ApplicationScoped
public class PaymentDLQRepository implements PanacheRepositoryBase<PaymentDLQEntity, Long>
{}
