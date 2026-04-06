package org.acme.api.rest.controller;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import jakarta.transaction.Transactional;
import org.acme.api.rest.dto.inventory.CarDTO;
import org.acme.api.rest.dto.reservation.ReservationDTO;
import org.acme.db.psql.entity.reservation.mapper.ReservationEntityMapper;
import org.acme.db.psql.repository.reservation.ReservationRepository;
import org.acme.db.psql.repository.user.UserRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;


@QuarkusTest
public class ReservationControllerTest
{
    String endpoint = "/api/rest/reservation";

    @Test
    public void testGetAvailabilityAndItsOrderedResult()
    {
        List<CarDTO> cars = RestAssured.given()
                .queryParam("from", LocalDate.now().toString())
                .queryParam("to", LocalDate.now().plusDays(1).toString())
                .when().get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getList("", CarDTO.class);
        int i=0;
        for (i=1; i<cars.size(); i++)
            if (cars.get(i-1).getId() > cars.get(i).getId())
                break;
        MatcherAssert.assertThat("Available car list is Id ordered ascending", i==cars.size());
    }

    @Test
    @Transactional // delete
    public void testMakeReservation()
    {
        String token = RestAssured.given()
                            .contentType("application/json")
                            .body(Map.of("username", "manager", "password", "manager"))
                            .when()
                            .post("/api/rest/user/signin")
                            .then()
                            .statusCode(200)
                            .extract().path("token");

        ReservationDTO dto = new ReservationDTO();
        dto.setCarId(1L);
        dto.setUserId(1L);
        dto.setDateTo(Instant.now().plus(1, ChronoUnit.DAYS));
        dto.setDateFrom(Instant.now());

        dto = RestAssured.given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
                .body(dto)
                .when()
                .post("/api/rest/reservation")
                .then()
                .statusCode(201)
                .extract().as(ReservationDTO.class);

        ReservationRepository delete = new ReservationRepository();
        delete.delete("id", dto.getId());
    }
}
