package org.acme.app.model.car;

public class BrandModel
{
    private Short id;
    private String name;

    public BrandModel ()
    {
        this.id = null;
        this.name = null;
    }

    public void setId(Short id)
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
