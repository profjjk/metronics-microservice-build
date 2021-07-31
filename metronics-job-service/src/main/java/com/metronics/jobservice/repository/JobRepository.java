package com.metronics.jobservice.repository;

import com.metronics.jobservice.dto.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByDateCompleted(String date_completed);
    List<Job> findByStatus(String status);
    List<Job> findByCustomerId(Integer id);
}
