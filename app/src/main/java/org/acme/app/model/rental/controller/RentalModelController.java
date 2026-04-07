package org.acme.app.model.rental.controller;

import org.acme.app.model.rental.RentalModel;

public class RentalModelController
{
    public static void startRental(RentalModel rental)
    {
        rental.setId(null);
        rental.setReservationId(rental.getReservationId());
        rental.setStartDate(rental.getStartDate());
        rental.setEndDate(null);
        rental.setAmount(null);
    }
    public static void endRental(RentalModel rental)
    {
        rental.setId(rental.getId());
        rental.setReservationId(rental.getReservationId());
        rental.setStartDate(null);
        rental.setEndDate(rental.getEndDate());
        rental.setAmount(null);
    }
}
