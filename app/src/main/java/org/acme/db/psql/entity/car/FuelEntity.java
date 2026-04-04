package org.acme.db.psql.entity.car;

import jakarta.persistence.*;

@Entity(name="fuel")
public class FuelEntity
{
    @Id
    @Column(name="id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fuel_id_sequence"
    )
    @SequenceGenerator(
            name = "fuel_id_sequence",
            sequenceName = "fuel_id_sequence",
            allocationSize = 1
    )
    private Short id;
    private String name;

    public FuelEntity()
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
