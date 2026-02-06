package com.example.demo;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;

    private LocalDateTime closeDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String link;

    public JobListing() {}

    public JobListing(String title, String company, LocalDateTime closeDate, String description, String link) {
        this.title = title;
        this.company = company;
        this.closeDate = closeDate;
        this.description = description;
        this.link = link;
    }
    public Long getId() {
    return id;
    }

public void setId(Long id) {
    this.id = id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getCompany() {
    return company;
}

public void setCompany(String company) {
    this.company = company;
}

public LocalDateTime getCloseDate() {
    return closeDate;
}

public void setCloseDate(LocalDateTime closeDate) {
    this.closeDate = closeDate;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public String getLink() {
    return link;
}

public void setLink(String link) {
    this.link = link;
}
}
    















