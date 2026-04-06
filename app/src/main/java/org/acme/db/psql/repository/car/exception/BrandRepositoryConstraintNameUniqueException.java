package org.acme.db.psql.repository.car.exception;

public class BrandRepositoryConstraintNameUniqueException extends RuntimeException
{
    public BrandRepositoryConstraintNameUniqueException()
    {
        super();
    }

    public BrandRepositoryConstraintNameUniqueException(String message)
    {
        super(message);
    }
}
