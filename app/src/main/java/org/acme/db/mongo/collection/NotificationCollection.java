package org.acme.db.mongo.collection;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.db.mongo.document.NotificationDocument;
import org.bson.types.ObjectId;

@ApplicationScoped
public class NotificationCollection implements PanacheMongoRepositoryBase<NotificationDocument, ObjectId>
{}
