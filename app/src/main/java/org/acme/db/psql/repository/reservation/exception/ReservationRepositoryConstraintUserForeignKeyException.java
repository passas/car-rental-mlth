package org.acme.db.psql.repository.reservation.exception;

public class ReservationRepositoryConstraintUserForeignKeyException extends RuntimeException
{
    public ReservationRepositoryConstraintUserForeignKeyException()
    {
        super();
    }

    public ReservationRepositoryConstraintUserForeignKeyException(String message)
    {
        super(message);
    }
}
