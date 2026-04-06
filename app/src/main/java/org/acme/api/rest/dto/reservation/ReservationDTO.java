package org.acme.api.rest.dto.reservation;

import java.time.Instant;

public class ReservationDTO
{
    private Long id;
    private Long carId;
    private Long userId;
    private Instant dateFrom;
    private Instant dateTo;

    public ReservationDTO()
    {
        this.id = null;
        this.carId = null;
        this.userId = null;
        this.dateFrom = null;
        this.dateTo = null;
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

    public Long getCarId()
    {
        return this.carId;
    }

    public Long getUserId()
    {
        return this.userId;
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
                + ", carId: " + this.carId
                + ", userId: " + this.userId
                + ", dateFrom: " + this.dateFrom
                + ", dateTo: " + this.dateTo
                + "]";
    }
}
