package org.acme.app.model.rental;

import java.math.BigDecimal;
import java.time.Instant;

public class RentalModel
{
    private Long id;
    private Long reservationId;
    private Instant startDate;
    private Instant endDate;
    private BigDecimal amount;

    public RentalModel()
    {
        this.id = null;
        this.reservationId = null;
        this.startDate = null;
        this.endDate = null;
        this.amount = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setReservationId(Long reservationId)
    {
        this.reservationId = reservationId;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void setStartDate(Instant startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(Instant endDate)
    {
        this.endDate = endDate;
    }

    public Long getId()
    {
        return this.id;
    }

    public Long getReservationId()
    {
        return this.reservationId;
    }

    public BigDecimal getAmount()
    {
        return this.amount;
    }

    public Instant getStartDate()
    {
        return this.startDate;
    }

    public Instant getEndDate()
    {
        return this.endDate;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", reservationId: " + this.reservationId
                + ", amount: " + this.amount
                + ", startDate: " + this.startDate
                + ", endDate: " + this.endDate
                + "]";
    }
}
