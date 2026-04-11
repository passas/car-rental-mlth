package org.acme.bus.kafka.consumer;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.bus.kafka.event.PaymentEvent;
import org.acme.db.mongo.collection.NotificationCollection;
import org.acme.db.mongo.document.NotificationDocument;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

import java.time.Instant;

@ApplicationScoped
public class PaymentEventConsumer
{
    private Logger log;
    private NotificationCollection notificationCollection;

    public PaymentEventConsumer(NotificationCollection notificationCollection)
    {
        this.log = Logger.getLogger(PaymentEventConsumer.class.getSimpleName());
        this.notificationCollection = notificationCollection;
    }

    @Incoming("payment-topic-in")
    public void receive(PaymentEvent event)
    {
        this.log.infof("Reading Kafka Payment Event...");
        this.log.infof("Kafka Payment Event read:\n %s", event);
        NotificationDocument notificationDocument = new NotificationDocument();
        notificationDocument.setRentalId(event.getRentalId());
        notificationDocument.setPaymentId(event.getPaymentId());
        notificationDocument.setSubject("Rental #" + event.getRentalId() + " Successfully Payed");
        notificationDocument.setMessage("You're $" + event.getAmount() + " rental price was payed.");
        notificationDocument.setAtInstant(Instant.now());
        this.log.infof("Storing notification...\n %s", notificationDocument);
        this.notificationCollection.persist(notificationDocument);
        this.log.infof("Notification stored:\n %s", notificationDocument);
    }
}