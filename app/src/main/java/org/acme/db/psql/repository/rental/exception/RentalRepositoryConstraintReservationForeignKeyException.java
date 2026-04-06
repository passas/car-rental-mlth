package org.acme.db.psql.repository.rental.exception;

public class RentalRepositoryConstraintReservationForeignKeyException extends RuntimeException
{
    public RentalRepositoryConstraintReservationForeignKeyException()
    {
        super();
    }

    public RentalRepositoryConstraintReservationForeignKeyException(String message)
    {
        super(message);
    }
}
