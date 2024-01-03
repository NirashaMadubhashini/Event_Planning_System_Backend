package com.spring.eventPlaning.repo;

import com.spring.eventPlaning.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepo extends JpaRepository<Services, Long> {
}
