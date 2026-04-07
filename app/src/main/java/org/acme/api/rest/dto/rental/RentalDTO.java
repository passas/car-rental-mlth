package org.acme.api.rest.dto.rental;

import java.math.BigDecimal;
import java.time.Instant;

public class RentalDTO
{
    private Long id;
    private Long reservationId;
    private Instant startDate;
    private Instant endDate;
    private BigDecimal amount;

    public RentalDTO()
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

    public Long getReservationId()
    {
        return this.reservationId;
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
                + ", reservationId: " + this.reservationId
                + ", startDate: " + this.startDate
                + ", endDate: " + this.endDate
                + ", amount: " + this.amount
                + "]";
    }
}