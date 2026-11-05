package com.rupsa.jobs.service;

import com.rupsa.jobs.model.Job;
import com.rupsa.jobs.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class JobService {
    private final JobRepository repository;
    public JobService(JobRepository repository){this.repository=repository;}
    public List<Job> search(String term,String location,String type){
        return repository.search(blankToNull(term),blankToNull(location),blankToNull(type));
    }
    public Job create(Job job){return repository.save(job);}
    @Transactional
    public Job update(Long id,Job input){
        Job current=repository.findById(id).orElseThrow(()->new JobNotFoundException(id));
        current.update(input.getTitle(),input.getCompany(),input.getLocation(),input.getEmploymentType(),input.getDescription());
        return current;
    }
    public void delete(Long id){
        if(!repository.existsById(id)) throw new JobNotFoundException(id);
        repository.deleteById(id);
    }
    private String blankToNull(String value){return value==null||value.isBlank()?null:value.trim();}
    public static class JobNotFoundException extends RuntimeException {
        public JobNotFoundException(Long id){super("Job "+id+" was not found");}
    }
}