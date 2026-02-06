package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    
    List<JobListing> findByCompanyContainingIgnoreCase(String company);
    List<JobListing> findByTitleContainingIgnoreCase(String title);
}
