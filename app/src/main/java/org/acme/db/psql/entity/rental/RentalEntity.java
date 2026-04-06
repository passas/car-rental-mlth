package org.acme.db.psql.entity.rental;

import jakarta.persistence.*;
import org.acme.db.psql.entity.reservation.ReservationEntity;

import java.math.BigDecimal;
import java.time.Instant;

@Entity(name="rental")
public class RentalEntity
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_rental_id")
    @SequenceGenerator(name = "seq_rental_id", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private ReservationEntity reservationEntity;

    @Column(name="start_date")
    private Instant startDate;

    @Column(name="end_date")
    private Instant endDate;

    private BigDecimal amount;

    public RentalEntity()
    {
        this.id = null;
        this.reservationEntity = null;
        this.startDate = null;
        this.endDate = null;
        this.amount = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setReservationEntity(ReservationEntity reservationEntity)
    {
        this.reservationEntity = reservationEntity;
    }

    public void setStartDate(Instant startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(Instant endDate)
    {
        this.endDate = endDate;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public Long getId()
    {
        return this.id;
    }

    public ReservationEntity getReservationEntity()
    {
        return this.reservationEntity;
    }

    public Instant getStartDate()
    {
        return this.startDate;
    }

    public Instant getEndDate()
    {
        return this.endDate;
    }

    public BigDecimal getAmount()
    {
        return this.amount;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", reservationEntity: " + this.reservationEntity
                + ", startDate: " + this.startDate
                + ", endDate: " + this.endDate
                + ", amount: " + this.amount
                + "]";
    }
}