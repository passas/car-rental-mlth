package org.acme.bus.kafka.producer.exception;

public class PaymentProducerWriteException extends RuntimeException
{
    public PaymentProducerWriteException()
    {
        super();
    }

    public PaymentProducerWriteException(String message)
    {
        super(message);
    }
}
