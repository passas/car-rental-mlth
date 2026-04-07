package org.acme.api.rest.dto.payment.mapper;

import org.acme.api.rest.dto.payment.PaymentDTO;
import org.acme.app.model.payment.PaymentModel;

public class PaymentDTOMapper
{
    public static PaymentDTO fromModel(PaymentModel model)
    {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(model.getId());
        dto.setRentalId(model.getRentalId());
        dto.setAmount(model.getAmount());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setPayedAt(model.getPayedAt());
        return dto;
    }
}
