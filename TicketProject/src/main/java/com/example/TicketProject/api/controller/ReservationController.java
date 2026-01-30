package com.example.TicketProject.api.controller;

import com.example.TicketProject.api.dto.EventResponseDTO;
import com.example.TicketProject.api.dto.ReservationCreateDTO;
import com.example.TicketProject.api.dto.ReservationResponseDTO;
import com.example.TicketProject.model.Reservation;
import com.example.TicketProject.service.EventsService;
import com.example.TicketProject.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/controller/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Validated @RequestBody ReservationCreateDTO reservation) {
        ReservationResponseDTO response = reservationService.create(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


}
