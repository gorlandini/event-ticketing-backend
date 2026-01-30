package com.example.TicketProject.service;

import com.example.TicketProject.api.dto.EventCreateDTO;
import com.example.TicketProject.api.dto.EventDetailsDTO;
import com.example.TicketProject.api.dto.EventListAllResponseDTO;
import com.example.TicketProject.api.dto.EventResponseDTO;
import com.example.TicketProject.model.Event;
import com.example.TicketProject.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EventsService {

    private static final Logger log =
            LoggerFactory.getLogger(EventsService.class);

    @Autowired
    private EventRepository eventRepository;

    public EventResponseDTO create(EventCreateDTO eventCreateDTO) {



        System.out.println("Criando evento: " + eventCreateDTO);

        System.out.println("Criando evento: " + eventCreateDTO.name());

        Event event = new Event();
        event.setName(eventCreateDTO.name());
        event.setDescription(eventCreateDTO.description());
        event.setTotalTickets(eventCreateDTO.totalTickets());
        event.setAvailableTickets(eventCreateDTO.totalTickets());
        event.setCreatedAt(LocalDateTime.now());
        event.setStartsAt(eventCreateDTO.startsAt());

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

    @Cacheable("events")
    public Page<EventListAllResponseDTO> listEvents( @PageableDefault(
                                                             size = 10,
                                                             sort = "createdAt",
                                                             direction = Sort.Direction.DESC
                                                     ) Pageable pageable)
    {
        log.info("Accessing database");
        return eventRepository.findAll(pageable).map(EventListAllResponseDTO::from);

    }
}
