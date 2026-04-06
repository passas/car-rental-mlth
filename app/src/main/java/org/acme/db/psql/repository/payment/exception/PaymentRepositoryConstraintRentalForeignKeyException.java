package org.acme.db.psql.repository.payment.exception;

public class PaymentRepositoryConstraintRentalForeignKeyException extends RuntimeException
{
    public PaymentRepositoryConstraintRentalForeignKeyException()
    {
        super();
    }

    public PaymentRepositoryConstraintRentalForeignKeyException(String message)
    {
        super(message);
    }
}
