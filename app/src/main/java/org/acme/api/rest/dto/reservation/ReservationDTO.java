package org.acme.api.rest.dto.reservation;

import java.time.Instant;

public class ReservationDTO
{
    private Long id;
    private Long carId;
    private Long userId;
    private Instant fromDate;
    private Instant toDate;

    public ReservationDTO()
    {
        this.id = null;
        this.carId = null;
        this.userId = null;
        this.fromDate = null;
        this.toDate = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setCarId(Long carId)
    {
        this.carId = carId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
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

    public Long getCarId()
    {
        return this.carId;
    }

    public Long getUserId()
    {
        return this.userId;
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
                + ", carId: " + this.carId
                + ", userId: " + this.userId
                + ", fromDate: " + this.fromDate
                + ", toDate: " + this.toDate
                + "]";
    }
}
