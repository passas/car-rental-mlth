package org.acme.bus.kafka.consumer;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.bus.kafka.event.PaymentEvent;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PaymentEventConsumer
{
    private Logger log;

    public PaymentEventConsumer()
    {
        this.log = Logger.getLogger(PaymentEventConsumer.class.getSimpleName());
    }

    @Incoming("payment-topic-in")
    public void receive(PaymentEvent event)
    {
        this.log.infof("Reading Kafka Payment Event...");
        this.log.infof("Kafka Payment Event read:\n %s", event);
    }
}