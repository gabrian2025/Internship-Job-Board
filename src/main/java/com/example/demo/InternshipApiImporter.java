
package com.example.demo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class InternshipApiImporter {

    private static final Logger logger = LoggerFactory.getLogger(InternshipApiImporter.class);
    private final JobService jobService;
    @Value("${rapidapi.key}")  // ← This reads from application.properties
    private String apiKey;

    public InternshipApiImporter(JobService jobService) {
        this.jobService = jobService;
    }

    public void fetchAndSaveInternships() {
        try {
            
            String url = "https://internships-api.p.rapidapi.com/active-jb-7d";

            // Create RestTemplate
            RestTemplate restTemplate = new RestTemplate();

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("X-RapidAPI-Key", apiKey);
            headers.set("X-RapidAPI-Host", "internships-api.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Make API call
            ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            logger.info("API Status: {}", response.getStatusCode());

            // Parse JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            // Loop through jobs
            int count = 0;
            for (JsonNode job : root) {
                logger.info("Raw JSON for job: {}", job.toString());

                String title = job.path("title").asText();
                String company = job.path("organization").asText();
                String closeDateStr = job.path("date_validthrough").asText();
                String description = job.path("linkedin_org_description").asText();
                String link = job.path("external_apply_url").asText();

               logger.info("Extracted - company: '{}', closeDate: '{}', link: '{}'", 
    company, closeDateStr, link);
                // Parse date
                LocalDateTime closeDate = null;
                if (closeDateStr != null && !closeDateStr.isEmpty()) {
                    try {
                        closeDate = LocalDateTime.parse(closeDateStr, DateTimeFormatter.ISO_DATE_TIME);
                    } catch (Exception e) {
                        logger.warn("Failed to parse date: {}", closeDateStr);
                    }
                }

                jobService.saveJob(title, company, closeDate, description, link);
                count++;
            }
            logger.info("Successfully saved {} jobs", count);
            
        } catch (Exception e) {
            logger.error("Failed to fetch and save internships", e);
            throw new RuntimeException("API import failed", e);
        }
    }
}