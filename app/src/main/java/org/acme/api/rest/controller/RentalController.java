package org.acme.api.rest.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.api.rest.dto.rental.RentalDTO;
import org.acme.api.rest.dto.rental.mapper.RentalDTOMapper;
import org.acme.app.model.rental.RentalModel;
import org.acme.app.model.rental.controller.RentalModelController;
import org.acme.app.model.rental.mapper.RentalModelMapper;
import org.acme.app.service.rental.RentalService;
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

    public RentalController(JsonWebToken jwt, RentalService rentalService)
    {
        this.log = Logger.getLogger(RentalController.class.getSimpleName());
        this.jwt = jwt;
        this.rentalService = rentalService;
    }

    @POST
    @Path("/start")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin", "manager", "system"})
    public Response startRental(RentalDTO rentalDTO)
    {
        this.log.infof("IN  /api/rest/rental/start POST\n %s", rentalDTO);
        Object data = null;
        Response response = null;
        Response.Status status = null;
        try
        {
            RentalModel rentalModel = RentalModelMapper.fromDTO(rentalDTO);
            RentalModelController.startRental(rentalModel, Instant.from(LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()));
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
            json.put("message", "rental already finished or still in progress");
            data = json;
            status = Response.Status.CONFLICT;
        }
        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
        this.log.infof("OUT /api/rest/rental/start POST\n status = %d\n headers = %s\n data = %s", response.getStatus(), response.getHeaders(), response.getEntity());
        return response;
    }

//    @POST
//    @Path("/end")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"admin", "manager", "user", "system"})
//    public Response endRental(RentalDTO rentalDTO)
//    {
//        Object data = null;
//        Response response = null;
//        Response.Status status = null;
//        this.log.infof("IN /api/rest/rental/end POST %s", rentalDTO);
//        RentalModel rentalModel = RentalModelMapper.fromDTO(rentalDTO);
//        if (this.rentalService.isManageableRental(rentalModel))
//        {
//            try
//            {
//                rentalModel = this.rentalService.endRental(rentalModel);
//                data = RentalDTOMapper.fromModel(rentalModel);
//                status = Response.Status.OK;
//            }
//            catch (RentalRepositoryConstraintDateEndAfterDateStartException e)
//            {
//                LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
//                json.put("message", "ending date prior to starting date");
//                status = Response.Status.CONFLICT;
//                data = json;
//            }
//        }
//        else if (!this.rentalService.isRentalStarted(rentalModel))
//        {
//            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
//            json.put("message", "rental did not start");
//            status = Response.Status.BAD_REQUEST;
//            data = json;
//        }
//        else
//        {
//            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
//            json.put("message", "rental already have been fully processed");
//            status = Response.Status.BAD_REQUEST;
//            data = json;
//        }
//        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
//        this.log.debugf("OUT /api/rest/rental/end POST [ %d | %s | %s ]", response.getStatus(), response.getHeaders(), response.getEntity());
//        this.log.infof("OUT /api/rest/rental/end POST [ %d | %s | .. ]", response.getStatus(), response.getHeaders());
//        return response;
//    }
//
//    @GET
//    @Path("/{id}")
//    @RolesAllowed({"admin", "manager", "user", "system"})
//    public Response fetch(@PathParam("id") Long id)
//    {
//        Object data = null;
//        Response response = null;
//        Response.Status status = null;
//        this.log.infof("IN /api/rest/rental/fetch/%d GET", id);
//        RentalModel rentalModel = new RentalModel();
//        rentalModel.setId(id);
//        rentalModel = this.rentalService.getRental(rentalModel);
//        if (rentalModel != null)
//        {
//            status = Response.Status.OK;
//            data = rentalModel;
//        }
//        else
//        {
//            status = Response.Status.BAD_REQUEST;
//        }
//        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
//        this.log.tracef("IN /api/rest/rental/fetch/%d GET [ %d | %s | %s ]", id, response.getStatus(), response.getHeaders(), response.getEntity());
//        this.log.infof("IN /api/rest/rental/fetch/%d GET [ %d | %s | .. ]", id, response.getStatus(), response.getHeaders());
//        return response;
//    }
}
