package com.spring.eventPlaning.repo;

import com.spring.eventPlaning.enums.AvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {
    List<Vendor> findByAvailability(AvailabilityStatus availabilityStatus);

    @Query("SELECT v FROM Vendor v WHERE v.serviceName = ?1")
    List<Vendor> findByServiceName(String serviceType);


}
