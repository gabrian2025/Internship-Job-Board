package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    private final InternshipApiImporter importer;

    public DemoApplication(InternshipApiImporter importer) {
        this.importer = importer;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runImporter() {
        return args -> {
            importer.fetchAndSaveInternships();
            System.out.println("API data fetched and saved to database!");
        };
    }
}
