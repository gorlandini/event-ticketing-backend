package com.example.TicketProject.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ReservationDTO(

        @NotBlank(message = "Client ID should not be blank")
        int customerId



){}
