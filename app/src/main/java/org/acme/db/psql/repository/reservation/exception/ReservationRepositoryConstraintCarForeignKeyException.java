package org.acme.db.psql.repository.reservation.exception;

public class ReservationRepositoryConstraintCarForeignKeyException extends RuntimeException
{
    public ReservationRepositoryConstraintCarForeignKeyException()
    {
        super();
    }

    public ReservationRepositoryConstraintCarForeignKeyException(String message)
    {
        super(message);
    }
}
