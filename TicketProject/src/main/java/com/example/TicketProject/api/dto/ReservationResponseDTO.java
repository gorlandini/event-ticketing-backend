package com.example.TicketProject.api.dto;

import com.example.TicketProject.model.Reservation;


public record ReservationResponseDTO(
        Long reservationId,
        Long customerId,
        Long eventId
) {
    public static ReservationResponseDTO from(Reservation reservation) {
        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getCustomerId(),
                reservation.getId()
        );
    }
}
