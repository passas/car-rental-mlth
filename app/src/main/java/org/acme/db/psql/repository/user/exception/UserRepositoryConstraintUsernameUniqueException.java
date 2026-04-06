package org.acme.db.psql.repository.user.exception;

public class UserRepositoryConstraintUsernameUniqueException extends RuntimeException
{
    public UserRepositoryConstraintUsernameUniqueException()
    {
        super();
    }

    public UserRepositoryConstraintUsernameUniqueException(String message)
    {
        super(message);
    }
}
