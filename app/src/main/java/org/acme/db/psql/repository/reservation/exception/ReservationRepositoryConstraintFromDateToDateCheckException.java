package org.acme.db.psql.repository.reservation.exception;

public class ReservationRepositoryConstraintFromDateToDateCheckException extends RuntimeException
{
    public ReservationRepositoryConstraintFromDateToDateCheckException()
    {
        super();
    }

    public ReservationRepositoryConstraintFromDateToDateCheckException(String message)
    {
        super(message);
    }
}
