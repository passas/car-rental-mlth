package org.acme.api.rest.dto.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;


public class BrandModelDTO
{
    private Integer id;
    private String name;
    private String variant;
    @JsonProperty("brand") private BrandDTO brandDTO;
    @JsonProperty("fuel") private FuelDTO fuelDTO;

    public BrandModelDTO()
    {
        this.id = null;
        this.name = null;
        this.variant = null;
        this.brandDTO = null;
        this.fuelDTO = null;
    }

    public void setId(int id)
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

    public void setBrandDTO(BrandDTO brandDTO)
    {
        this.brandDTO = brandDTO;
    }

    public void setFuelDTO(FuelDTO fuelDTO)
    {
        this.fuelDTO = fuelDTO;
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

    public BrandDTO getBrandDTO()
    {
        return this.brandDTO;
    }

    public FuelDTO getFuelDTO()
    {
        return this.fuelDTO;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", name: " + this.name
                + ", variant: " + this.variant
                + ", brandDTO: " + this.brandDTO
                + ", fuelDTO: " + this.fuelDTO
                + "]";
    }
}
