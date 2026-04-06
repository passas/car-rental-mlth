package org.acme.db.psql.repository.reservation.exception;

public class ReservationRepositoryConstraintPeriodOverlapExcludeException extends RuntimeException
{
    public ReservationRepositoryConstraintPeriodOverlapExcludeException()
    {
        super();
    }

    public ReservationRepositoryConstraintPeriodOverlapExcludeException(String message)
    {
        super(message);
    }
}
