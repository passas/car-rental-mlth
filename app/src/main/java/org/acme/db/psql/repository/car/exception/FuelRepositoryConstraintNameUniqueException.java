package org.acme.db.psql.repository.car.exception;

public class FuelRepositoryConstraintNameUniqueException extends RuntimeException
{
    public FuelRepositoryConstraintNameUniqueException()
    {
        super();
    }

    public FuelRepositoryConstraintNameUniqueException(String message)
    {
        super(message);
    }
}
