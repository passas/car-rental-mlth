package org.acme.db.psql.entity.car;

import jakarta.persistence.*;

@Entity(name="brand")
public class BrandEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_brand_id")
    @SequenceGenerator(name = "seq_brand_id", allocationSize = 1)
    private Short id;
    private String name;

    public BrandEntity()
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
