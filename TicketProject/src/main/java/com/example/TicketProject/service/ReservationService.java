package com.example.TicketProject.service;

import com.example.TicketProject.api.dto.ReservationCreateDTO;
import com.example.TicketProject.api.dto.ReservationResponseDTO;
import com.example.TicketProject.model.Event;
import com.example.TicketProject.model.Reservation;
import com.example.TicketProject.repository.EventRepository;
import com.example.TicketProject.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReservationService {


    private final EventRepository eventRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(EventRepository eventRepository,
                              ReservationRepository reservationRepository) {
        this.eventRepository = eventRepository;
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    public ReservationResponseDTO create(ReservationCreateDTO dto) {
        // Busca evento com lock para concorrência
        Event event = eventRepository.findByIdForUpdate(dto.eventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getAvailableTickets() <= 0) {
            throw new RuntimeException("Sold out");
        }

        // Decrementa tickets
        event.setAvailableTickets(event.getAvailableTickets() - 1);

        // Cria reserva
        Reservation reservation = new Reservation();
        reservation.setCustomerId(dto.customerId());
        reservation.setId(dto.eventId());
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setReserved(true);

        reservationRepository.save(reservation);

        return ReservationResponseDTO.from(reservation);
    }
}
