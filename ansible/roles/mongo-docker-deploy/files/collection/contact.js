db = db.getSiblingDB("car-rental-notification");

if (!db.getCollectionNames().includes("contact"))
    db.createCollection("contact");

db.contact.createIndex({ user_id: 1 });
db.contact.createIndex({ email: 1 }, { unique: true });

if (db.contact.countDocuments() === 0)
{
    db.contact.insertOne({
        user_id: 0,
        email: null,
        on: new Date()
    });
}