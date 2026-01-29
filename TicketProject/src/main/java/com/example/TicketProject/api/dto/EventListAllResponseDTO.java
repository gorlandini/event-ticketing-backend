package com.example.TicketProject.api.dto;


import com.example.TicketProject.model.Event;

import java.time.LocalDateTime;

public record EventListAllResponseDTO(
            Long id,
            String name,
            LocalDateTime startsAt


    ) {

    public static EventListAllResponseDTO from(Event event) {
        return new EventListAllResponseDTO(
                event.getId(),
                event.getName(),
                event.getStartsAt()
        );
    }


}


