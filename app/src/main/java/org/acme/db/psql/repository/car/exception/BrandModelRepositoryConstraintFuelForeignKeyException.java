package org.acme.db.psql.repository.car.exception;

public class BrandModelRepositoryConstraintFuelForeignKeyException extends RuntimeException
{
    public BrandModelRepositoryConstraintFuelForeignKeyException()
    {
        super();
    }

    public BrandModelRepositoryConstraintFuelForeignKeyException(String message)
    {
        super(message);
    }
}
