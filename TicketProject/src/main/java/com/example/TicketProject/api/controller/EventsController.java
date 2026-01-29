package com.example.TicketProject.api.controller;

import com.example.TicketProject.api.dto.EventCreateDTO;
import com.example.TicketProject.api.dto.EventDetailsDTO;
import com.example.TicketProject.api.dto.EventListAllResponseDTO;
import com.example.TicketProject.api.dto.EventResponseDTO;
import com.example.TicketProject.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/controller/events")
public class EventsController {


    @Autowired
    private EventsService eventService;

    @GetMapping("/testing")
    public String test() {
        return "testing";
    }


    @GetMapping("details/{id}")
    public ResponseEntity<EventDetailsDTO> getDetails(@PathVariable Long id) {

        return eventService.getDetails(id)
                .map(ResponseEntity::ok)          // 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404
    }


    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(
            @RequestBody EventCreateDTO dto
    ) {
        EventResponseDTO response = eventService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public Page<EventListAllResponseDTO> listEvents(
            @PageableDefault(
                    size = 10,
                    sort = "startsAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable)

    {

        return eventService.listEvents(pageable);
    }

}
