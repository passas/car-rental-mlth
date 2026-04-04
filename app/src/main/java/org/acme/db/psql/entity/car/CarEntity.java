package org.acme.db.psql.entity.car;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name="car")
public class CarEntity
{
    @Id
    @Column(name="id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_id_sequence"
    )
    @SequenceGenerator(
            name = "car_id_sequence",
            sequenceName = "car_id_sequence",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_model_id",
            foreignKey = @ForeignKey(name = "car_brand_model")
    )
    private BrandModelEntity brandModelEntity;

    @Column(name="license_plate", unique = true)
    private String licensePlate;

    private BigDecimal tank;
    private BigDecimal distance;

    public CarEntity()
    {
        this.id = null;
        this.brandModelEntity = null;
        this.licensePlate = null;
        this.tank = null;
        this.distance = null;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setBrandModelEntity(BrandModelEntity brandModelEntity)
    {
        this.brandModelEntity = brandModelEntity;
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

    public Long getId()
    {
        return this.id;
    }

    public BrandModelEntity getBrandModelEntity()
    {
        return this.brandModelEntity;
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

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", brandModelEntity: " + this.brandModelEntity
                + ", licensePlate: " + this.licensePlate
                + ", tank: " + this.tank
                + ", distance: " + this.distance
                + "]";
    }
}
