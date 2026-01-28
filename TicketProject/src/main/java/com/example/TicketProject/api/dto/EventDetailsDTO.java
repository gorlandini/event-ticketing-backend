package com.example.TicketProject.api.dto;


import java.time.LocalDateTime;

public record EventDetailsDTO(
            Long id,
            String name,
            String description,
            LocalDateTime eventDate,
            int availableTickets

    ) {}



