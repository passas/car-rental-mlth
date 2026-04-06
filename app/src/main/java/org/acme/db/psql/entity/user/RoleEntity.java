package org.acme.db.psql.entity.user;

import jakarta.persistence.*;

@Entity(name = "role")
public class RoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role_id")
    @SequenceGenerator(name = "seq_role_id", allocationSize = 1)
    private Short id;
    private String name;

    public RoleEntity()
    {
        this.id = null;
        this.name = null;
    }

    public void setId (Short id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public short getId()
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
