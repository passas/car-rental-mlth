package org.acme.api.rest.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.api.rest.dto.payment.mapper.PaymentDTOMapper;
import org.acme.api.rest.dto.rental.RentalDTO;
import org.acme.api.rest.dto.rental.mapper.RentalDTOMapper;
import org.acme.app.model.payment.PaymentModel;
import org.acme.app.model.payment.controller.PaymentModelController;
import org.acme.app.model.rental.RentalModel;
import org.acme.app.model.rental.controller.RentalModelController;
import org.acme.app.model.rental.mapper.RentalModelMapper;
import org.acme.app.service.payment.PaymentService;
import org.acme.app.service.rental.RentalService;
import org.acme.db.psql.repository.payment.exception.PaymentRepositoryConstraintRentalForeignKeyException;
import org.acme.db.psql.repository.payment.exception.PaymentRepositoryConstraintRentalUniqueException;
import org.acme.db.psql.repository.rental.exception.RentalRepositoryConstraintEndDateCheckException;
import org.acme.db.psql.repository.rental.exception.RentalRepositoryConstraintReservationForeignKeyException;
import org.acme.db.psql.repository.rental.exception.RentalRepositoryConstraintReservationUniqueException;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;


@Path("/api/rest/rental")
public class RentalController
{
    private Logger log;
    private JsonWebToken jwt;
    private RentalService rentalService;
    private PaymentService paymentService;

    public RentalController(JsonWebToken jwt, RentalService rentalService, PaymentService paymentService)
    {
        this.log = Logger.getLogger(RentalController.class.getSimpleName());
        this.jwt = jwt;
        this.rentalService = rentalService;
        this.paymentService = paymentService;
    }

    @POST
    @Path("/start")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "manager", "system"})
    public Response startRental(RentalDTO rentalDTO)
    {
        this.log.infof("IN  /api/rest/rental/start POST\n payload=%s", rentalDTO);
        Object data = null;
        Response response = null;
        Response.Status status = null;
        try
        {
            RentalModel rentalModel = RentalModelMapper.fromDTO(rentalDTO);
            rentalModel.setStartDate(Instant.from(LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()));
            RentalModelController.startRental(rentalModel);
            rentalDTO = RentalDTOMapper.fromModel(this.rentalService.createRental(rentalModel));
            data = rentalDTO;
            status = Response.Status.CREATED;
        }
        catch (RentalRepositoryConstraintReservationForeignKeyException e)
        {
            LinkedHashMap<String, String> json = new LinkedHashMap<>();
            json.put("message", "reservation does not exists");
            data = json;
            status = Response.Status.BAD_REQUEST;
        }
        catch (RentalRepositoryConstraintReservationUniqueException e)
        {
            LinkedHashMap<String, String> json = new LinkedHashMap<>();
            json.put("message", "rental have been created already");
            data = json;
            status = Response.Status.CONFLICT;
        }
        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
        this.log.infof("OUT /api/rest/rental/start POST\n status=%d\n headers=%s\n data=%s", response.getStatus(), response.getHeaders(), response.getEntity());
        return response;
    }

    @POST
    @Path("/end")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "manager", "system"})
    public Response endRental(RentalDTO rentalDTO)
    {
        this.log.infof("IN /api/rest/rental/end POST\n payload=%s", rentalDTO);
        Object data = null;
        Response response = null;
        Response.Status status = null;
        RentalModel rentalModel = RentalModelMapper.fromDTO(rentalDTO);
        if (this.rentalService.isRentalStarted(rentalModel) == false)
        {
            status = Response.Status.BAD_REQUEST;
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "rental did not began yet, either exists");
            data = json;
        }
        else if (this.rentalService.isManageableRental(rentalModel))
        {
            try
            {
                rentalModel.setEndDate(Instant.from(LocalDateTime.now().atZone(ZoneId.of("UTC"))));
                RentalModelController.endRental(rentalModel);
                rentalModel = this.rentalService.endRental(rentalModel);
                try
                {
                    PaymentModel paymentModel = new PaymentModel();
                    paymentModel.setRentalId(rentalModel.getId());
                    paymentModel.setAmount(rentalModel.getAmount());
                    paymentModel.setCreatedAt(Instant.from(LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()));
                    PaymentModelController.createPayment(paymentModel);
                    paymentModel = this.paymentService.createPayment(paymentModel);

                    status = Response.Status.OK;
                    LinkedHashMap<String, Object> json = new LinkedHashMap<>(2);
                    json.put("rental", RentalDTOMapper.fromModel(rentalModel));
                    json.put("payment", PaymentDTOMapper.fromModel(paymentModel));
                    data = json;
                }
                catch (PaymentRepositoryConstraintRentalForeignKeyException e)
                {
                    status = Response.Status.BAD_REQUEST;
                    LinkedHashMap<String, Object> json = new LinkedHashMap<>(1);
                    json.put("message", "there is no such rental");
                    data = json;
                }
                catch (PaymentRepositoryConstraintRentalUniqueException e)
                {
                    status = Response.Status.CONFLICT;
                    LinkedHashMap<String, Object> json = new LinkedHashMap<>(1);
                    json.put("message", "there's already a payment associated to this rental");
                    data = json;
                }
            }
            catch (RentalRepositoryConstraintEndDateCheckException e)
            {
                status = Response.Status.INTERNAL_SERVER_ERROR;
                LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
                json.put("message", "end date was found prior starting date, contact support");
                data = json;
            }
        }
        else
        {
            status = Response.Status.BAD_REQUEST;
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "rental has already been fully processed");
            data = json;
        }
        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
        this.log.infof("OUT /api/rest/rental/end POST\n status=%d\n headers=%s\n data=%s", response.getStatus(), response.getHeaders(), response.getEntity());
        return response;
    }
}
