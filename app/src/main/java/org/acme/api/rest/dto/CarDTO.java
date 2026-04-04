package org.acme.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CarDTO
{
    private Long id;
    private String licensePlate;
    private BigDecimal tank;
    private BigDecimal distance;
    @JsonProperty("brandModel") private BrandModelDTO brandModelDTO;

    public CarDTO()
    {
        this.id = null;
        this.licensePlate = null;
        this.tank = null;
        this.distance = null;
        this.brandModelDTO = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setLicensePLate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public void setTank(BigDecimal tank)
    {
        this.tank = tank;
    }

    public void setDistance(BigDecimal distance)
    {
        this.distance = distance;
    }

    public void setBrandModelDTO(BrandModelDTO brandModelDTO)
    {
        this.brandModelDTO = brandModelDTO;
    }

    public Long getId()
    {
        return this.id;
    }

    public String getLicensePlate()
    {
        return this.licensePlate;
    }

    public BigDecimal getTank()
    {
        return this.tank;
    }

    public BigDecimal getDistance()
    {
        return this.distance;
    }

    public BrandModelDTO getBrandModelDTO()
    {
        return this.brandModelDTO;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", licensePlate: " + this.licensePlate
                + ", tank: " + this.tank
                + ", distance: " + this.distance
                + ", brandModelDTO: " + this.brandModelDTO
                + "]";
    }
}
