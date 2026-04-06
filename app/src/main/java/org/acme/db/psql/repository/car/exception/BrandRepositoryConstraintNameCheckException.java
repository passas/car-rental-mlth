package org.acme.db.psql.repository.car.exception;

public class BrandRepositoryConstraintNameCheckException extends RuntimeException
{
    public BrandRepositoryConstraintNameCheckException()
    {
        super();
    }

    public BrandRepositoryConstraintNameCheckException(String message)
    {
        super(message);
    }
}
