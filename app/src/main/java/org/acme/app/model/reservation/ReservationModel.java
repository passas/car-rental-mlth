package org.acme.app.model.reservation;

import java.time.Instant;

public class ReservationModel
{
    private Long id;
    private Long userId;
    private Long carId;
    private Instant fromDate;
    private Instant toDate;

    public ReservationModel()
    {
        this.id = null;
        this.userId = null;
        this.carId = null;
        this.fromDate = null;
        this.toDate = null;
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

    public void setFromDate(Instant fromDate)
    {
        this.fromDate = fromDate;
    }

    public void setToDate(Instant toDate)
    {
        this.toDate = toDate;
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

    public Instant getFromDate()
    {
        return this.fromDate;
    }

    public Instant getToDate()
    {
        return this.toDate;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", userId: " + this.userId
                + ", carId: " + this.carId
                + ", fromDate: " + this.fromDate
                + ", toDate: " + this.toDate
                + "]";
    }
}
