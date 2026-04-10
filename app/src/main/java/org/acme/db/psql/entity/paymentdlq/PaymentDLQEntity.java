package org.acme.db.psql.entity.paymentdlq;

import jakarta.persistence.*;
import org.acme.db.psql.entity.payment.PaymentEntity;

@Entity(name="payment_dlq")
public class PaymentDLQEntity
{
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private PaymentEntity paymentEntity;

    public PaymentDLQEntity()
    {
        this.paymentEntity = null;
    }

    public void setPaymentEntity(PaymentEntity paymentEntity)
    {
        this.paymentEntity = paymentEntity;
    }

    public PaymentEntity getPaymentEntity()
    {
        return this.paymentEntity;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "paymentEntity: " + this.paymentEntity
                + "]";
    }

}
