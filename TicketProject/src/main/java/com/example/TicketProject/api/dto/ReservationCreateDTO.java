package com.example.TicketProject.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationCreateDTO(

        @NotNull(message = "Client ID should not be blank")
        @Min(value = 1, message = "customerId ID must be greater than 0")
        Long customerId,

        @NotNull(message = "Event Id should not be null")
        @Min(value = 1, message = "Event ID must be greater than 0")
        Long eventId



){}
