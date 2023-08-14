package com.spring.eventPlaning.repo;

import com.spring.eventPlaning.entity.Vendor;
import com.spring.eventPlaning.enums.AvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {
    List<Vendor> findByAvailability(AvailabilityStatus availabilityStatus);


}
