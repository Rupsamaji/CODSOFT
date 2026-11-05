package com.rupsa.jobs.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "jobs")
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120) private String title;
    @Column(nullable = false, length = 120) private String company;
    @Column(nullable = false, length = 100) private String location;
    @Column(nullable = false, length = 40) private String employmentType;
    @Column(nullable = false, length = 4000) private String description;
    @Column(nullable = false) private Instant createdAt = Instant.now();

    protected Job() {}
    public Job(String title, String company, String location, String employmentType, String description) {
        this.title=title; this.company=company; this.location=location;
        this.employmentType=employmentType; this.description=description;
    }
    public Long getId(){return id;} public String getTitle(){return title;}
    public String getCompany(){return company;} public String getLocation(){return location;}
    public String getEmploymentType(){return employmentType;} public String getDescription(){return description;}
    public Instant getCreatedAt(){return createdAt;}
    public void update(String title,String company,String location,String employmentType,String description){
        this.title=title; this.company=company; this.location=location;
        this.employmentType=employmentType; this.description=description;
    }
}