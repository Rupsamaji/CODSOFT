package com.rupsa.jobs.repository;

import com.rupsa.jobs.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(""" 
        select j from Job j
        where (:term is null or lower(j.title) like lower(concat('%', :term, '%'))
          or lower(j.company) like lower(concat('%', :term, '%')))
          and (:location is null or lower(j.location) like lower(concat('%', :location, '%')))
          and (:type is null or lower(j.employmentType) = lower(:type))
        order by j.createdAt desc
        """)
    List<Job> search(@Param("term") String term, @Param("location") String location, @Param("type") String type);
}