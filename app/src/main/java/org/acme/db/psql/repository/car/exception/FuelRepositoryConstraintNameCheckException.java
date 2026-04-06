package org.acme.db.psql.repository.car.exception;

public class FuelRepositoryConstraintNameCheckException extends RuntimeException
{
    public FuelRepositoryConstraintNameCheckException()
    {
        super();
    }

    public FuelRepositoryConstraintNameCheckException(String message)
    {
        super(message);
    }
}
