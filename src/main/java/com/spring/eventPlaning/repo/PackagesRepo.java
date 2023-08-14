package com.spring.eventPlaning.repo;

import com.spring.eventPlaning.entity.Packages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackagesRepo extends JpaRepository<Packages, Long> {
}
