package org.acme.db.psql.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "role")
public class RoleEntity
{
    @Id
    @Column(name="id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_id_sequence"
    )
    @SequenceGenerator(
            name = "role_id_sequence",
            sequenceName = "role_id_sequence",
            allocationSize = 1
    )
    @JsonIgnore
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
