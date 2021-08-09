package com.metronics.metronicsjobservice.repository;

import com.metronics.metronicsjobservice.dto.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
}
