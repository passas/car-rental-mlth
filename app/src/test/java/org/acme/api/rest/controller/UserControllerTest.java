package org.acme.api.rest.controller;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import org.acme.api.rest.dto.user.UserDTO;
import org.acme.db.psql.repository.user.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;


@QuarkusTest
public class UserControllerTest
{
    String endpoint_signup = "/api/rest/user/signup";
    String endpoint_signin = "/api/rest/user/signin";

    @Test
    @Transactional // delete
    public void testCreateUser()
    {
        String username = "u0";
        String password = "u0";

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);

        RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(userDTO)
                    .when().post(endpoint_signup)
                    .then()
                    .statusCode(201);

        UserRepository delete = new UserRepository();
        delete.delete(delete.findByUsername(username));
    }

    @Test
    @Transactional // delete
    public void testUserAlreadyExists()
    {
        String username = "u0";
        String password = "u0";

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .when().post(endpoint_signup)
                .then()
                .statusCode(201);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .when().post(endpoint_signup)
                .then()
                .statusCode(409);

        UserRepository delete = new UserRepository();
        delete.delete(delete.findByUsername(username));
    }

    @Test
    @Transactional // delete
    public void testLoginUser()
    {
        String username = "u0";
        String password = "u0";

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .when().post(endpoint_signup)
                .then()
                .statusCode(201);

        RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(userDTO)
                    .when()
                        .post(endpoint_signin)
                    .then()
                        .statusCode(200)
                        .body("$", Matchers.hasKey("token"))
                        .body("$", Matchers.hasKey("expire"));
        //.body("token", notNullValue())
        //.body("expire", notNullValue());
        UserRepository delete = new UserRepository();
        delete.delete(delete.findByUsername(username));
    }

    @Test
    public void testLoginUserNotRegistered()
    {
        String username = "u0";
        String password = "u0";

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .when()
                .post(endpoint_signin)
                .then()
                .statusCode(401);
    }

    @Test
    @Transactional // delete
    public void testLoginWrongCredentials()
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("u0");
        userDTO.setPassword("u0");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .when().post(endpoint_signup)
                .then()
                .statusCode(201);

        userDTO.setPassword("password");
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userDTO)
                .when()
                .post(endpoint_signin)
                .then()
                .statusCode(401);

        UserRepository delete = new UserRepository();
        delete.delete(delete.findByUsername(userDTO.getUsername()));
    }
}
