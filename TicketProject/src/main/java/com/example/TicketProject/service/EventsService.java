package com.example.TicketProject.service;

import com.example.TicketProject.api.dto.EventCreateDTO;
import com.example.TicketProject.api.dto.EventDetailsDTO;
import com.example.TicketProject.api.dto.EventResponseDTO;
import com.example.TicketProject.model.Event;
import com.example.TicketProject.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EventsService {

    @Autowired
    private EventRepository eventRepository;

    public EventResponseDTO create(EventCreateDTO eventCreateDTO) {



        System.out.println("Criando evento: " + eventCreateDTO);

        System.out.println("Criando evento: " + eventCreateDTO.name());

        Event event = new Event();
        event.setName(eventCreateDTO.name());
        event.setDescription(eventCreateDTO.description());
        event.setTotalTickets(eventCreateDTO.totalTickets());
        event.setCreatedAt(LocalDateTime.now());

        Event saved = eventRepository.save(event);

        return new EventResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getCreatedAt()

        );

    }

    public Optional<EventDetailsDTO> getDetails(Long id) {
        return eventRepository.findById(id)
                .map(event -> new EventDetailsDTO(
                        event.getId(),
                        event.getName(),
                        event.getDescription(),
                        event.getStartsAt(),
                        event.getAvailableTickets()
                ));
    }
}
