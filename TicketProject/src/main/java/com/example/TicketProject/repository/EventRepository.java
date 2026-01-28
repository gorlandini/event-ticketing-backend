package com.example.TicketProject.repository;

import com.example.TicketProject.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findById(Long id);
}
