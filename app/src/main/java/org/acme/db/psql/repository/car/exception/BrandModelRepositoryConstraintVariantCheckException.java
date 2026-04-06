package org.acme.db.psql.repository.car.exception;

public class BrandModelRepositoryConstraintVariantCheckException extends RuntimeException
{
    public BrandModelRepositoryConstraintVariantCheckException()
    {
        super();
    }

    public BrandModelRepositoryConstraintVariantCheckException(String message)
    {
        super(message);
    }
}
