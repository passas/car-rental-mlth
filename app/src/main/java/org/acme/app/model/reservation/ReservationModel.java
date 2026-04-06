package org.acme.app.model.reservation;

import java.time.Instant;

public class ReservationModel
{
    private Long id;
    private Long userId;
    private Long carId;
    private Instant dateFrom;
    private Instant dateTo;

    public ReservationModel()
    {
        this.id = null;
        this.userId = null;
        this.carId = null;
        this.dateFrom = null;
        this.dateTo = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public void setCarId(Long carId)
    {
        this.carId = carId;
    }

    public void setDateFrom(Instant dateFrom)
    {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Instant dateTo)
    {
        this.dateTo = dateTo;
    }

    public Long getId()
    {
        return this.id;
    }

    public Long getUserId()
    {
        return this.userId;
    }

    public Long getCarId()
    {
        return this.carId;
    }

    public Instant getDateFrom()
    {
        return this.dateFrom;
    }

    public Instant getDateTo()
    {
        return this.dateTo;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", userId: " + this.userId
                + ", carId: " + this.carId
                + ", dateFrom: " + this.dateFrom
                + ", dateTo: " + this.dateTo
                + "]";
    }
}
