package org.acme.app.service.user.exception;

public class WrongCredentialsException extends RuntimeException
{
    public WrongCredentialsException()
    {
        super();
    }

    public WrongCredentialsException(String message)
    {
        super(message);
    }
}
