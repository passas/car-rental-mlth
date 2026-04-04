package org.acme.api.rest.controller;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;


@QuarkusTest
public class CarControllerTest
{
    String endpoint = "/api/rest/car/";

    @Test
    public void testGet()
    {
        RestAssured.given()
                    .when().get(endpoint)
                    .then()
                    .statusCode(200)
                    .body("", Matchers.notNullValue())
                    .body("", Matchers.instanceOf(List.class))
                    .body("", Matchers.hasSize(Matchers.greaterThan(0)));
    }
}
