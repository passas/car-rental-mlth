package org.acme.app.model.payment.controller;

import org.acme.app.model.payment.PaymentModel;

public class PaymentModelController
{
    public static void createPayment(PaymentModel payment)
    {
        payment.setId(null);
        payment.setRentalId(payment.getRentalId());
        payment.setAmount(payment.getAmount());
        payment.setCreatedAt(payment.getCreatedAt());
        payment.setPayedAt(null);
    }

    public static void closePayment(PaymentModel payment)
    {
        payment.setId(payment.getId());
        payment.setRentalId(payment.getRentalId());
        payment.setAmount(null);
        payment.setCreatedAt(null);
        payment.setPayedAt(payment.getPayedAt());
    }
}
