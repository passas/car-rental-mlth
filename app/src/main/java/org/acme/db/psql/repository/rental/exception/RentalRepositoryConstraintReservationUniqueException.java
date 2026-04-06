package org.acme.db.psql.repository.rental.exception;

public class RentalRepositoryConstraintReservationUniqueException extends RuntimeException
{
    public RentalRepositoryConstraintReservationUniqueException()
    {
        super();
    }

    public RentalRepositoryConstraintReservationUniqueException(String message)
    {
        super(message);
    }
}
