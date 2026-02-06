package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")  // Allow frontend to access
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Get all jobs
    @GetMapping
    public List<JobListing> getAllJobs() {
        return jobService.getAllJobs();
    }

    // Get job by ID
    @GetMapping("/{id}")
    public JobListing getJobById(@PathVariable Long id) {
        return jobService.getJobById(id);
    }

    // Search by company
    @GetMapping("/search/company")
    public List<JobListing> searchByCompany(@RequestParam String company) {
        return jobService.findByCompany(company);
    }

    // Search by title
    @GetMapping("/search/title")
    public List<JobListing> searchByTitle(@RequestParam String keyword) {
        return jobService.findByTitleContaining(keyword);
    }
}