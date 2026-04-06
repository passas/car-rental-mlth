package org.acme.api.rest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.api.rest.dto.inventory.CarDTO;
import org.acme.api.rest.dto.inventory.mapper.CarDTOMapper;
import org.acme.app.service.car.CarService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Path("/api/rest/car")
public class CarController
{
    private Logger log;
    private CarService carService;

    public CarController(CarService carService)
    {
        this.log = Logger.getLogger(CarController.class.getSimpleName());
        this.carService = carService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll()
    {
        Object data;
        Response.Status status;
        Response response;
        this.log.infof("IN /api/rest/car GET");
        List<CarDTO> all = this.carService.getAll().stream().map(CarDTOMapper::fromModel).collect(Collectors.toList());
        data = all;
        status = Response.Status.OK;
        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
        this.log.infof("OUT /api/rest/car GET\n status = %d\n headers = %s\n data = (%d elements)", response.getStatus(), response.getHeaders(), ((List<CarDTO>)response.getEntity()).size());
        return response;
    }
}
