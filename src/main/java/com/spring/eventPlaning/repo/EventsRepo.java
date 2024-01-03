package com.spring.eventPlaning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepo extends JpaRepository<Event, Long> {
}
