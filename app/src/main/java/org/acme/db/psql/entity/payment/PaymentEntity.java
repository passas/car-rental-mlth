package org.acme.db.psql.entity.payment;

import jakarta.persistence.*;
import org.acme.db.psql.entity.rental.RentalEntity;

import java.math.BigDecimal;
import java.time.Instant;

@Entity(name="payment")
public class PaymentEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_payment_id")
    @SequenceGenerator(name = "seq_payment_id", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id")
    private RentalEntity rentalEntity;

    private BigDecimal amount;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "payed_at")
    private Instant payedAt;

    public PaymentEntity()
    {
        this.id = null;
        this.rentalEntity = null;
        this.amount = null;
        this.createdAt = null;
        this.payedAt = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setRentalEntity(RentalEntity rentalEntity)
    {
        this.rentalEntity = rentalEntity;
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

    public RentalEntity getRentalEntity()
    {
        return this.rentalEntity;
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
                + "rentalEntity: " + this.rentalEntity
                + "amount: " + this.amount
                + "createdAt: " + this.createdAt
                + "payedAt: " + this.payedAt
                + "]";
    }

}
