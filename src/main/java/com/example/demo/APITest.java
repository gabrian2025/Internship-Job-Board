package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class APITest {

    public static void main(String[] args) throws Exception {

        
        String apiKey = "311730afe3msh3c1df35ed83be2fp1d6465jsncce2e623f3da";

        
        String url = 
            "https://internships-api.p.rapidapi.com/active-jb-7d";

        // 1. Create RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // 2. Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", "internships-api.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 3. Make API call
        ResponseEntity<String> response =
            restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        System.out.println("Status: " + response.getStatusCode());

        // 4. Parse JSON
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());

        // 5. Loop through jobs
        for (JsonNode job : root) {
            String title = job.path("title").asText();
            String company = job.path("organization").asText();
            String closeDate = job.path("date_validthrough").asText();
            String description = job.path("linkedin_org_description").asText();
            String link = job.path("external_apply_url").asText();
            

            System.out.println("---------------");
            System.out.println("Title: " + title);
            System.out.println("Company: " + company);
            System.out.println("closeDate" + closeDate);
            System.out.println("Description" + description);
            System.out.println("link " + link );
            
        }
    }
}