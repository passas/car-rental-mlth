package org.acme.db.psql.repository.rental.exception;

public class RentalRepositoryConstraintEndDateCheckException extends RuntimeException
{
    public RentalRepositoryConstraintEndDateCheckException()
    {
        super();
    }

    public RentalRepositoryConstraintEndDateCheckException(String message)
    {
        super(message);
    }
}
