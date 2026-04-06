package org.acme.db.psql.repository.car.exception;

public class BrandModelRepositoryConstraintBrandForeignKeyException extends RuntimeException
{
    public BrandModelRepositoryConstraintBrandForeignKeyException()
    {
        super();
    }

    public BrandModelRepositoryConstraintBrandForeignKeyException(String message)
    {
        super(message);
    }
}
