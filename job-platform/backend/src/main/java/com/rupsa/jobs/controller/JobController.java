package com.rupsa.jobs.controller;

import com.rupsa.jobs.model.Job;
import com.rupsa.jobs.service.JobService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5500"})
public class JobController {
    private final JobService service;
    public JobController(JobService service){this.service=service;}

    public record JobRequest(@NotBlank @Size(max=120) String title,
      @NotBlank @Size(max=120) String company, @NotBlank @Size(max=100) String location,
      @NotBlank @Size(max=40) String employmentType, @NotBlank @Size(max=4000) String description) {
        Job toJob(){return new Job(title.trim(),company.trim(),location.trim(),employmentType.trim(),description.trim());}
    }

    @GetMapping public List<Job> search(@RequestParam(required=false) String q,
      @RequestParam(required=false) String location,@RequestParam(required=false) String type){
        return service.search(q,location,type);
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Job create(@Valid @RequestBody JobRequest request){return service.create(request.toJob());}
    @PutMapping("/{id}") public Job update(@PathVariable Long id,@Valid @RequestBody JobRequest request){
        return service.update(id,request.toJob());
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){service.delete(id);}
}