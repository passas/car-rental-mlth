package org.acme.db.psql.repository.payment.exception;

public class PaymentRepositoryConstraintRentalUniqueException extends RuntimeException
{
    public PaymentRepositoryConstraintRentalUniqueException()
    {
        super();
    }

    public PaymentRepositoryConstraintRentalUniqueException(String message)
    {
        super(message);
    }
}
