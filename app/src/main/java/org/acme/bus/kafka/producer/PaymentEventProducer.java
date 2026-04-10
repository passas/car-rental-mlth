package org.acme.bus.kafka.producer;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.bus.kafka.dlq.PaymentDLQ;
import org.acme.bus.kafka.event.PaymentEvent;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;


@ApplicationScoped
public class PaymentEventProducer
{
    private Logger log;
    private Emitter<PaymentEvent> emitter;
    private PaymentDLQ paymentDLQ;

    public PaymentEventProducer(@Channel("payment-topic-out") Emitter<PaymentEvent> emitter, PaymentDLQ paymentDLQ)
    {
        this.log = Logger.getLogger(PaymentEventProducer.class.getSimpleName());
        this.emitter = emitter;
        this.paymentDLQ = paymentDLQ;
    }

    public void send(PaymentEvent event)
    {
        this.log.infof("Writing Kafka Payment Event:\n %s", event);
        this.emitter.send(event)
                    .whenComplete((_, throwable) ->
                    {
                        if (throwable != null)
                        {
                            this.log.errorf("Kafka Payment Event not written...\n %s", throwable.toString());
                            this.paymentDLQ.enqueue(event);
                        }
                        this.log.infof("Kafka Payment Event successfully written:\n %s", event);
                    });
    }

//    public void send1(PaymentEvent event) throws PaymentProducerWriteException
//    {
//        CompletionStage<Void> ack = this.emitter.send(event);
//        ack.thenRun(() ->
//        {   // ACK
//            this.log.infof("Kafka Payment Event successfully written:\n %s", event);
//        })
//        .exceptionally(throwable ->
//        {
//            // NACK
//            this.log.errorf("Kafka Payment Event not written...\n %s", throwable.toString());
//            System.out.println("Message nacked: " + throwable.getMessage());
//            throw new PaymentProducerWriteException();
//        });
//    }
}
