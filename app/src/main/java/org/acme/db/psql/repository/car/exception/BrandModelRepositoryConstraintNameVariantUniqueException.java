package org.acme.db.psql.repository.car.exception;

public class BrandModelRepositoryConstraintNameVariantUniqueException extends RuntimeException
{
    public BrandModelRepositoryConstraintNameVariantUniqueException()
    {
        super();
    }

    public BrandModelRepositoryConstraintNameVariantUniqueException(String message)
    {
        super(message);
    }
}
