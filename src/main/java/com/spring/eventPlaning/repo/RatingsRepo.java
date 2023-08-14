package com.spring.eventPlaning.repo;

import com.spring.eventPlaning.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingsRepo extends JpaRepository<Ratings, Long> {
    List<Ratings> findByVendorNic(String vendorNic);
}
