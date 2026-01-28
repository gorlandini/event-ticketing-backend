package com.example.TicketProject.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record EventCreateDTO(
        @NotBlank(message = "Event name must not be blank")
        String name,

        @NotBlank(message = "Description must not be blank")
        String description,

        @NotBlank(message = "The date should be informed")
        LocalDateTime startsAt,

        @Positive(message = "Total tickets must be greater than zero")
        int totalTickets
) {}
