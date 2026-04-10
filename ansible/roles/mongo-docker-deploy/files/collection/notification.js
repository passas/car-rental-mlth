db = db.getSiblingDB("car-rental-notification");

if (!db.getCollectionNames().includes("notification"))
    db.createCollection("notification");

db.notification.createIndex({ user_id: 1 });
db.notification.createIndex({ rental_id: 1 });

if (db.notification.countDocuments() === 0)
{
    db.notification.insertOne({
        user_id: 0,
        rental_id: 0,
        subject: "Car Rental Notification",
        message: "This is a test notification",
        at: new Date(),
        sent: null
    });
}