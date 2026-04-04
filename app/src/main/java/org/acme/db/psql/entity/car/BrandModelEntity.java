package org.acme.db.psql.entity.car;

import jakarta.persistence.*;

@Entity(name="brand_model")
public class BrandModelEntity
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "brand_model_id_sequence"
    )
    @SequenceGenerator(
            name = "brand_model_id_sequence",
            sequenceName = "brand_model_id_sequence",
            allocationSize = 1
    )
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id",
            foreignKey = @ForeignKey(name = "brand_model_brand")
    )
    private BrandEntity brandEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fuel_id",
            foreignKey = @ForeignKey(name = "brand_model_fuel")
    )
    private FuelEntity fuelEntity;

    private String name;
    private String variant;

    public BrandModelEntity()
    {
        this.id = null;
        this.name = null;
        this.variant = null;
        this.brandEntity = null;
        this.fuelEntity = null;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setVariant(String variant)
    {
        this.variant = variant;
    }

    public void setBrandEntity(BrandEntity brandEntity)
    {
        this.brandEntity = brandEntity;
    }

    public void setFuelEntity(FuelEntity fuelEntity)
    {
        this.fuelEntity = fuelEntity;
    }

    public Integer getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getVariant()
    {
        return this.variant;
    }

    public BrandEntity getBrandEntity()
    {
        return this.brandEntity;
    }

    public FuelEntity getFuelEntity()
    {
        return this.fuelEntity;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", name: " + this.name
                + ", variant: " + this.variant
                + ", brandEntity: " + this.brandEntity
                + ", fuelEntity: " + this.fuelEntity
                + "]";
    }
}
