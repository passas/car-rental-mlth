package org.acme.app.model.rental.controller;

import org.acme.app.model.rental.RentalModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class RentalModelController
{
    public static void startRental(RentalModel rental, Instant startDate)
    {
        rental.setId(null);
        rental.setReservationId(rental.getReservationId());
        rental.setDateStart(startDate);
        rental.setDateEnd(null);
        rental.setAmount(null);
    }
}
