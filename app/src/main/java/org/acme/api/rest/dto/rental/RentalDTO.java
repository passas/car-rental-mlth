package org.acme.api.rest.dto.rental;

import java.math.BigDecimal;
import java.time.Instant;

public class RentalDTO
{
    private Long id;
    private Long reservationId;
    private Instant dateStart;
    private Instant dateEnd;
    private BigDecimal amount;

    public RentalDTO()
    {
        this.id = null;
        this.reservationId = null;
        this.dateStart = null;
        this.dateEnd = null;
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

    public void setDateStart(Instant dateStart)
    {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Instant dateEnd)
    {
        this.dateEnd = dateEnd;
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

    public Instant getDateStart()
    {
        return this.dateStart;
    }

    public Instant getDateEnd()
    {
        return this.dateEnd;
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
                + ", dateStart: " + this.dateStart
                + ", dateEnd: " + this.dateEnd
                + ", amount: " + this.amount
                + "]";
    }
}