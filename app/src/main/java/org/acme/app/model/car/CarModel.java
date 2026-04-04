package org.acme.app.model.car;

import java.math.BigDecimal;

public class CarModel
{
    private Long id;
    private String licensePlate;
    private BigDecimal tank;
    private BigDecimal distance;
    private BrandModelModel brandModelModel;

    public CarModel()
    {
        this.id = null;
        this.licensePlate = null;
        this.tank = null;
        this.distance = null;
        this.brandModelModel = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate)
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

    public void setBrandModelModel(BrandModelModel brandModelModel)
    {
        this.brandModelModel = brandModelModel;
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

    public BrandModelModel getBrandModelModel()
    {
        return this.brandModelModel;
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
                + ", brandModelModel: " + this.brandModelModel
                + "]";
    }
}
