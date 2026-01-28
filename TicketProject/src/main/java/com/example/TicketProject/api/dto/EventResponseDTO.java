package com.example.TicketProject.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record EventResponseDTO(

        Long id,


        String name,





        LocalDateTime createdAt







){}