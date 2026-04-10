package org.acme.bus.kafka.event;

import java.math.BigDecimal;
import java.time.Instant;

public class PaymentEvent
{
    private Long paymentId;
    private Long rentalId;
    private BigDecimal amount;
    private Instant date;

    public PaymentEvent()
    {
        this.paymentId = null;
        this.rentalId = null;
        this.amount = null;
        this.date = null;
    }

    public void setPaymentId(Long paymentId)
    {
        this.paymentId = paymentId;
    }

    public void setRentalId(Long rentalId)
    {
        this.rentalId = rentalId;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void setDate (Instant date)
    {
        this.date = date;
    }

    public Long getPaymentId()
    {
        return this.paymentId;
    }

    public Long getRentalId()
    {
        return this.rentalId;
    }

    public Instant getDate()
    {
        return this.date;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "paymentId: " + this.paymentId
                + ", rentalId: " + this.rentalId
                + ", amount: " + this.amount
                + ", date: " + this.date
                + "]";
    }
}
