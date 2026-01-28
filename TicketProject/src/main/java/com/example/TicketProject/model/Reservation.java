package com.example.TicketProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    private  Long id;
    private LocalDateTime reservationDate;
    private Long customerId;
    private boolean reserved;
}
