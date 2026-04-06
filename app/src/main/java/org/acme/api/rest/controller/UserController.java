package org.acme.api.rest.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.api.rest.dto.user.UserDTO;
import org.acme.app.model.user.UserModel;
import org.acme.app.model.user.mapper.UserModelMapper;
import org.acme.app.service.user.UserService;
//import org.acme.app.service.exception.passwordservice.PasswordServicePasswordNotMatchException;
import org.acme.app.service.user.exception.UserNotFoundException;
import org.acme.app.service.user.exception.WrongCredentialsException;
import org.acme.db.psql.repository.user.exception.UserRepositoryConstraintUsernameUniqueException;
//import org.acme.db.psql.repository.user.exception.UserRepositoryUserNotFoundException;
import org.jboss.logging.Logger;

import java.util.LinkedHashMap;

@Path("/api/rest/user")
public class UserController
{
    private Logger log;
    private UserService userService;

    public UserController(UserService userService)
    {
        this.log = Logger.getLogger(UserController.class.getSimpleName());
        this.userService = userService;
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(UserDTO userDTO)
    {
        Object data = null;
        Response response = null;
        Response.Status status = null;
        this.log.infof("IN /api/rest/user/signup POST %s", userDTO);
        try
        {
            UserModel userModel = this.userService.createUser(UserModelMapper.fromDTO(userDTO));
            status = Response.Status.CREATED;
        }
        catch (UserRepositoryConstraintUsernameUniqueException e)
        {
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "username already exists");
            status = Response.Status.CONFLICT;
            data = json;
        }
        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
        this.log.infof("OUT /api/rest/user/signup POST\n status = %d\n headers = %s\n data = %s", response.getStatus(), response.getHeaders(), response.getEntity());
        return response;
    }

    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signin(UserDTO userDTO)
    {
        Object data = null;
        Response response = null;
        Response.Status status = null;
        this.log.infof("IN /api/rest/user/signin POST %s", userDTO);
        try
        {
            String token = this.userService.createToken(UserModelMapper.fromDTO(userDTO));
            Long lifespan = this.userService.getTokenExpiration();
            LinkedHashMap<String, Object> json = new LinkedHashMap<>(2);
            json.put("token", token);
            json.put("expire", lifespan);
            data = json;
            status = Response.Status.OK;
        }
        catch (UserNotFoundException | WrongCredentialsException e)
        {
            LinkedHashMap<String, String> json = new LinkedHashMap<>(1);
            json.put("message", "wrong credentials");
            data = json;
            status = Response.Status.UNAUTHORIZED;
        }
        response = Response.ok(data, MediaType.APPLICATION_JSON).status(status).build();
        this.log.infof("OUT /api/rest/user/signin POST\n status = %d", response.getStatus());
        return response;
    }
}
