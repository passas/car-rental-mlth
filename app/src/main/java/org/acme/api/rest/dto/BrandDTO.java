package org.acme.api.rest.dto;


public class BrandDTO
{
    private Short id;
    private String name;

    public BrandDTO()
    {
        this.id = null;
        this.name = null;
    }

    public void setId(short id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Short getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", name: " + this.name
                + "]";
    }
}
