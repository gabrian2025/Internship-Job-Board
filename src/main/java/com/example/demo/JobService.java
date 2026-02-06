package com.example.demo;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {

    private final JobListingRepository repo;

    public JobService(JobListingRepository repository) {
        this.repo = repository;
    }

    public void saveJob(String title, String company, LocalDateTime closeDate, 
                       String description, String link) {
        JobListing job = new JobListing(title, company, closeDate, description, link);
        repo.save(job);
    }

    public List<JobListing> getAllJobs() {
        return repo.findAll();
    }

    public JobListing getJobById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<JobListing> findByCompany(String company) {
        return repo.findByCompanyContainingIgnoreCase(company);
    }

    public List<JobListing> findByTitleContaining(String keyword) {
        return repo.findByTitleContainingIgnoreCase(keyword);
    }
}