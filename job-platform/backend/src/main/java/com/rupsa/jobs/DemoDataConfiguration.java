package com.rupsa.jobs;

import com.rupsa.jobs.model.Job;
import com.rupsa.jobs.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoDataConfiguration {
    @Bean
    CommandLineRunner seedJobs(JobRepository repository) {
        return args -> {
            if (repository.count() > 0) return;
            repository.save(new Job("Java Backend Intern", "Northstar Labs", "Kolkata", "Internship",
                "Build REST APIs, write maintainable Java code and learn Spring Boot with an engineering team."));
            repository.save(new Job("Frontend Developer", "Pixel Works", "Remote", "Full-time",
                "Create accessible, responsive interfaces and collaborate with product and design."));
            repository.save(new Job("Graduate Software Engineer", "Cloud Harbor", "Bengaluru", "Full-time",
                "Work on backend services, databases, testing and production engineering fundamentals."));
        };
    }
}