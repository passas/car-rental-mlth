package org.acme.db.psql.entity.payment.mapper;

import org.acme.app.model.payment.PaymentModel;
import org.acme.db.psql.entity.payment.PaymentEntity;
import org.acme.db.psql.entity.rental.RentalEntity;

public class PaymentEntityMapper
{
    public static PaymentEntity fromModel(PaymentModel model)
    {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(model.getId());
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setId(model.getRentalId());
        entity.setRentalEntity(rentalEntity);
        entity.setAmount(model.getAmount());
        entity.setCreatedAt(model.getCreatedAt());
        entity.setPayedAt(model.getPayedAt());
        return entity;
    }
}
