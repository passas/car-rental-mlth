package org.acme.api.rest.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.api.rest.dto.inventory.CarDTO;
import org.acme.api.rest.dto.inventory.mapper.CarDTOMapper;
import org.acme.api.rest.dto.reservation.ReservationDTO;
import org.acme.app.model.reservation.ReservationModel;
import org.acme.app.model.reservation.controller.ReservationModelController;
import org.acme.app.model.reservation.mapper.ReservationModelMapper;
import org.acme.app.service.reservation.ReservationService;
import org.acme.db.psql.repository.reservation.exception.ReservationRepositoryConstraintCarForeignKeyException;
import org.acme.db.psql.repository.reservation.exception.ReservationRepositoryConstraintFromDateToDateCheckException;
import org.acme.db.psql.repository.reservation.exception.ReservationRepositoryConstraintPeriodOverlapExcludeException;
import org.acme.db.psql.repository.reservation.exception.ReservationRepositoryConstraintUserForeignKeyException;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Path("/api/rest/reservation")
public class ReservationController
{
    private Logger log;
    private JsonWebToken jwt;
    private ReservationService reservationService;

    public ReservationController(JsonWebToken jwt, ReservationService reservationService)
    {
        this.log = Logger.getLogger(ReservationController.class.getSimpleName());
        this.jwt = jwt;
        this.reservationService = reservationService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailableCarsBetweenDates(@QueryParam("from") LocalDate from, @QueryParam("to") LocalDate to)
    {
        Object data;
        Response response;
        Response.Status status;
        this.log.infof("IN  /api/rest/reservation?from=%s&to=%s GET", from, to);
        if (from == null || to == null)
        {
            this.log.warnf("The request is in absence of query parameters:\n from=%s\n to=%s", from, to);
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "'from' and 'to' query parameters must be present");
            data = json;
            status = Response.Status.BAD_REQUEST;
            response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
            this.log.infof("OUT /api/rest/reservation?from=%s&to=%s GET\n status = %d\n headers = %s\n data = %s", from, to, response.getStatus(), response.getHeaders(), response.getEntity());
            return response;
        }
        TreeSet<CarDTO> available = this.reservationService.checkAvailableCars(from, to).stream().map(CarDTOMapper::fromModel).collect(Collectors.toCollection(TreeSet::new));
        data = available;
        status = Response.Status.OK;
        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
        this.log.infof("OUT /api/rest/reservation?from=%s&to=%s GET\n status = %d\n headers = %s\n data = (%d available cars)", from, to, response.getStatus(), response.getHeaders(), ((Set<CarDTO>)response.getEntity()).size());
        return response;
    }

    @POST
    @RolesAllowed({"admin", "manager", "system", "user"})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeReservation(ReservationDTO reservationDTO)
    {
        this.log.infof("IN  /api/rest/reservation POST\n user = %s\n payload = %s", this.jwt.getClaim("uid"), reservationDTO);
        Object data = null;
        Response response = null;
        Response.Status status = null;
        // let's go
        ReservationModel reservationModel = ReservationModelMapper.fromDTO(reservationDTO);
        // force I am
        if (this.jwt.getGroups().contains("user"))
            reservationModel.setUserId(this.jwt.getClaim("uid"));
        try
        {
            ReservationModelController.makeReservation(reservationModel);
            reservationModel = this.reservationService.createReservation(reservationModel);
            data = reservationModel;
            status = Response.Status.CREATED;
        }
        catch (ReservationRepositoryConstraintPeriodOverlapExcludeException e)
        {
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "car's already reserved on that period");
            data = json;
            status = Response.Status.CONFLICT;
        }
        catch (ReservationRepositoryConstraintFromDateToDateCheckException e)
        {
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "date from after date to");
            data = json;
            status = Response.Status.BAD_REQUEST;
        }
        catch (ReservationRepositoryConstraintCarForeignKeyException e)
        {
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "car not found");
            data = json;
            status = Response.Status.BAD_REQUEST;
        }
        catch (ReservationRepositoryConstraintUserForeignKeyException e)
        {
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "user not found");
            data = json;
            status = Response.Status.BAD_REQUEST;
        }
        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
        this.log.infof("OUT /api/rest/reservation POST\n status = %d\n headers = %s\n data = %s", response.getStatus(), response.getHeaders(), response.getEntity());
        return response;
    }
}

