package com.spring.eventPlaning.repo;

import com.spring.eventPlaning.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepo extends JpaRepository<Event, Long> {
}
