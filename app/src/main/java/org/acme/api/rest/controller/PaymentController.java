package org.acme.api.rest.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.acme.api.rest.dto.payment.PaymentDTO;
import org.acme.app.service.payment.PaymentService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

@Path("/api/rest/payment")
public class PaymentController
{
    private Logger log;
    private JsonWebToken jasonWebToken;
    private PaymentService paymentService;

    public PaymentController(JsonWebToken jsonWebToken, PaymentService paymentService)
    {
        this.log = Logger.getLogger(PaymentController.class.getSimpleName());
        this.jasonWebToken = jsonWebToken;
        this.paymentService = paymentService;
    }

    @Path("/pay")
    @POST
    @RolesAllowed({"admin", "manager", "system"})
    public Response pay(PaymentDTO paymentDTO)
    {
        Object data = null;
        Response response = null;
        Response.Status status = null;
        return Response.ok().entity(data).status(status).build();
    }
}
