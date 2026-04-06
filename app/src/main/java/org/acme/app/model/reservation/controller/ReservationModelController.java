package org.acme.app.model.reservation.controller;

import org.acme.app.model.reservation.ReservationModel;

public class ReservationModelController
{
    public static void makeReservation(ReservationModel reservation)
    {
        reservation.setId(null);
        reservation.setUserId(reservation.getUserId());
        reservation.setCarId(reservation.getCarId());
        reservation.setDateFrom(reservation.getDateFrom());
        reservation.setDateTo(reservation.getDateTo());
    }
}
