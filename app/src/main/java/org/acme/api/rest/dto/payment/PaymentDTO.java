package org.acme.api.rest.dto.payment;

import java.math.BigDecimal;
import java.time.Instant;

public class PaymentDTO
{
    private Long id;
    private Long rentalId;
    private BigDecimal amount;
    private Instant createdAt;
    private Instant payedAt;

    public PaymentDTO()
    {
        this.id = null;
        this.rentalId = null;
        this.amount = null;
        this.createdAt = null;
        this.payedAt = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setRentalId(Long rentalId)
    {
        this.rentalId = rentalId;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void setCreatedAt(Instant createdAt)
    {
        this.createdAt = createdAt;
    }

    public void setPayedAt(Instant payedAt)
    {
        this.payedAt = payedAt;
    }

    public Long getId()
    {
        return this.id;
    }

    public Long getRentalId()
    {
        return this.rentalId;
    }

    public BigDecimal getAmount()
    {
        return this.amount;
    }

    public Instant getCreatedAt()
    {
        return this.createdAt;
    }

    public Instant getPayedAt()
    {
        return this.payedAt;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", rentalId: " + this.rentalId
                + ", amount: " + this.amount
                + ", createdAt: " + this.createdAt
                + ", payedAt: " + this.payedAt
                + "]";
    }
}
