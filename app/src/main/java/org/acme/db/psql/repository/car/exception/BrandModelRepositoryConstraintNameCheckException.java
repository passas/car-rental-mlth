package org.acme.db.psql.repository.car.exception;

public class BrandModelRepositoryConstraintNameCheckException extends RuntimeException
{
    public BrandModelRepositoryConstraintNameCheckException()
    {
        super();
    }

    public BrandModelRepositoryConstraintNameCheckException(String message)
    {
        super(message);
    }
}
