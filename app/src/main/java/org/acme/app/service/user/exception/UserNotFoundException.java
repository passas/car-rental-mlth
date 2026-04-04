package org.acme.app.service.user.exception;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException()
    {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
