package org.acme.app.model.rental;

import java.math.BigDecimal;
import java.time.Instant;

public class RentalModel
{
    private Long id;
    private Long reservationId;
    private Instant dateStart;
    private Instant dateEnd;
    private BigDecimal amount;

    public RentalModel()
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

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void setDateStart(Instant dateStart)
    {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Instant dateEnd)
    {
        this.dateEnd = dateEnd;
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

    public Instant getDateStart()
    {
        return this.dateStart;
    }

    public Instant getDateEnd()
    {
        return this.dateEnd;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", reservationId: " + this.reservationId
                + ", amount: " + this.amount
                + ", dateStart: " + this.dateStart
                + ", dateEnd: " + this.dateEnd
                + "]";
    }
}
