package org.acme.app.model.payment.mapper;

import org.acme.api.rest.dto.payment.PaymentDTO;
import org.acme.app.model.payment.PaymentModel;
import org.acme.db.psql.entity.payment.PaymentEntity;

public class PaymentModelMapper
{
    public static PaymentModel fromDTO(PaymentDTO dto)
    {
        PaymentModel model = new PaymentModel();
        model.setId(dto.getId());
        model.setRentalId(dto.getRentalId());
        model.setAmount(dto.getAmount());
        model.setCreatedAt(dto.getCreatedAt());
        model.setPayedAt(dto.getPayedAt());
        return model;
    }

    public static PaymentModel fromEntity(PaymentEntity entity)
    {
        PaymentModel model = new PaymentModel();
        model.setId(entity.getId());
        model.setRentalId(entity.getRentalEntity().getId());
        model.setAmount(entity.getAmount());
        model.setCreatedAt(entity.getCreatedAt());
        model.setPayedAt(entity.getPayedAt());
        return model;
    }
}
