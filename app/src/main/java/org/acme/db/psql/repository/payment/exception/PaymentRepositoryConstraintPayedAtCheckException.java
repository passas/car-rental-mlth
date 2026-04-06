package org.acme.db.psql.repository.payment.exception;

public class PaymentRepositoryConstraintPayedAtCheckException extends RuntimeException
{
    public PaymentRepositoryConstraintPayedAtCheckException()
    {
        super();
    }

    public PaymentRepositoryConstraintPayedAtCheckException(String message)
    {
        super(message);
    }
}
