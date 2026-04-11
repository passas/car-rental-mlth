package org.acme.db.mongo.document;


import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.Instant;

@MongoEntity(database="car-rental-notification", collection="notification")
public class NotificationDocument
{
    @BsonId
    private ObjectId id;

    @BsonProperty("user_id")
    private Long userId;

    @BsonProperty("rental_id")
    private Long rentalId;

    @BsonProperty("payment_id")
    private Long paymentId;

    private String subject;
    private String message;

    @BsonProperty("at")
    private Instant atInstant;
    @BsonProperty("sent")
    private Instant sentInstant;

    public NotificationDocument()
    {
        this.id = null;
        this.userId = null;
        this.rentalId = null;
        this.paymentId = null;
        this.subject = null;
        this.message = null;
        this.atInstant = null;
        this.sentInstant = null;
    }

    public void setId(ObjectId id)
    {
        this.id = id;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public void setRentalId(Long rentalId)
    {
        this.rentalId = rentalId;
    }

    public void setPaymentId(Long paymentId)
    {
        this.paymentId = paymentId;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setAtInstant(Instant at)
    {
        this.atInstant = at;
    }

    public void setSentInstant(Instant at)
    {
        this.sentInstant = at;
    }

    public ObjectId getId()
    {
        return this.id;
    }

    public Long getUserId()
    {
        return this.userId;
    }

    public Long getRentalId()
    {
        return this.rentalId;
    }

    public Long getPaymentId()
    {
        return this.paymentId;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public String getMessage()
    {
        return this.message;
    }

    public Instant getAtInstant()
    {
        return this.atInstant;
    }

    public Instant getSentInstant()
    {
        return this.sentInstant;
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName()
                + "["
                + "id: " + this.id
                + ", userId: " + this.userId
                + ", rentalId: " + this.rentalId
                + ", paymentId: " + this.paymentId
                + ", subject: " + this.subject
                + ", message: " + this.message
                + ", atInstant: " + this.atInstant
                + ", sentInstant: " + this.sentInstant
                + "]";
    }
}