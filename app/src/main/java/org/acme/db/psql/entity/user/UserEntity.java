package org.acme.db.psql.entity.user;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "_user")
public class UserEntity
{
    @Id
    @Column(name="id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "_user_id_sequence"
    )
    @SequenceGenerator(
            name = "_user_id_sequence",
            sequenceName = "_user_id_sequence",
            allocationSize = 1
    )
    private Long id;
    private String username;
    private String password;
    @Column(name="is_active")
    private Boolean active;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public List<RoleEntity> roles;

    public UserEntity()
    {
        this.id = null;
        this.username = null;
        this.password = null;
        this.active = false;
        this.roles = new ArrayList<>();
    }

    public Long getId()
    {
        return this.id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public Boolean getActive()
    {
        return this.active;
    }

    public List<RoleEntity> getRoles()
    {
        return this.roles;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setActive(Boolean active)
    {
        this.active = active;
    }

    public void setRoles(List<RoleEntity> roles)
    {
        this.roles = roles;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", username: " + this.username
                + ", password: " + (this.password == null || this.password.isBlank() ? password : "****")
                + ", active: " + this.active
                + ", roles: " + this.roles
                + "]";
    }

    public Boolean isActive()
    {
        return this.active;
    }

    public void addRole(RoleEntity role)
    {
        if (this.roles == null)
            this.roles = new ArrayList<>();
        this.roles.add(role);
    }
}
