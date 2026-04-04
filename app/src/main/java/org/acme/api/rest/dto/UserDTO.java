package org.acme.api.rest.dto;

public class UserDTO
{
    private String username;
    private String password;

    public UserDTO()
    {
        this.username = null;
        this.password = null;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "username: " + this.username
                + ", password: " + (this.password != null ? "****" : null)
                + "]";
    }
}
