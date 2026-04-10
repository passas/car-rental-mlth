package org.acme.api.rest.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.api.rest.dto.payment.PaymentDTO;
import org.acme.api.rest.dto.payment.mapper.PaymentDTOMapper;
import org.acme.app.model.payment.PaymentModel;
import org.acme.app.model.payment.controller.PaymentModelController;
import org.acme.app.model.payment.mapper.PaymentModelMapper;
import org.acme.app.service.payment.PaymentService;
import org.acme.bus.kafka.event.PaymentEvent;
import org.acme.bus.kafka.producer.PaymentEventProducer;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;

@Path("/api/rest/payment")
public class PaymentController
{
    private Logger log;
    private JsonWebToken jasonWebToken;
    private PaymentService paymentService;
    private PaymentEventProducer paymentEventProducer;

    public PaymentController(JsonWebToken jsonWebToken, PaymentService paymentService, PaymentEventProducer paymentEventProducer)
    {
        this.log = Logger.getLogger(PaymentController.class.getSimpleName());
        this.jasonWebToken = jsonWebToken;
        this.paymentService = paymentService;
        this.paymentEventProducer = paymentEventProducer;
    }

    @Path("/pay")
    @POST
    @RolesAllowed({"admin", "manager", "system"})
    public Response pay(PaymentDTO paymentDTO)
    {
        this.log.infof("IN /api/rest/payment/pay POST\n payload=%s", paymentDTO);
        Object data = null;
        Response response = null;
        Response.Status status = null;
        PaymentModel paymentModel = PaymentModelMapper.fromDTO(paymentDTO);
        if (this.paymentService.isPaymentDueOpened(paymentModel) == false)
        {
            status = Response.Status.BAD_REQUEST;
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "payment due does not exists");
            data = json;
        }
        else if (this.paymentService.isPaymentDueClosed(paymentModel) == false)
        {
            try
            {
                paymentModel.setPayedAt(Instant.from(LocalDateTime.now().atZone(ZoneId.of("UTC"))));
                PaymentModelController.closePayment(paymentModel);
                paymentModel = this.paymentService.payPayment(paymentModel);
                status = Response.Status.OK;
                data = PaymentDTOMapper.fromModel(paymentModel);

                PaymentEvent paymentEvent = new PaymentEvent();
                paymentEvent.setPaymentId(paymentModel.getId());
                paymentEvent.setRentalId(paymentModel.getRentalId());
                paymentEvent.setAmount(paymentModel.getAmount());
                paymentEvent.setDate(paymentModel.getPayedAt());
                this.paymentEventProducer.send(paymentEvent);
            }
            catch (Exception e)
            {
                status = Response.Status.INTERNAL_SERVER_ERROR;
                LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
                json.put("message", "contact support");
                data = json;
            }
        }
        else
        {
            status = Response.Status.BAD_REQUEST;
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "payment due already payed");
            data = json;
        }
        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
        this.log.infof("OUT /api/rest/payment/pay POST\n status=%d\n headers=%s\n data=%s", response.getStatus(), response.getHeaders(), response.getEntity());
        return response;
    }
}
