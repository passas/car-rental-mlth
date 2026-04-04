package org.acme.app.model.car;

public class BrandModelModel
{
    private Integer id;
    private String name;
    private String variant;
    private BrandModel brandModel;
    private FuelModel fuelModel;

    public BrandModelModel()
    {
        this.id = null;
        this.name = null;
        this.variant = null;
        this.brandModel = null;
        this.fuelModel = null;
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

    public void setBrandModel(BrandModel brandModel)
    {
        this.brandModel = brandModel;
    }

    public void setFuelModel(FuelModel fuelModel)
    {
        this.fuelModel = fuelModel;
    }

    public Integer getId()
    {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getVariant() {
        return this.variant;
    }

    public BrandModel getBrandModel()
    {
        return this.brandModel;
    }

    public FuelModel getFuelModel()
    {
        return this.fuelModel;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", name: " + this.name
                + ", variant: " + this.name
                + ", brandModel: " + this.brandModel
                + ", fuelModel: " + this.fuelModel
                + "]";
    }

}
