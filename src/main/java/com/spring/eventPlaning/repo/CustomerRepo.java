package com.spring.eventPlaning.repo;

import com.spring.eventPlaning.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, String> {

}
