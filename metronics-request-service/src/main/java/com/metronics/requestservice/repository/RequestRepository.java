package com.metronics.requestservice.repository;

import com.metronics.requestservice.dto.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByDateCompleted(String date_completed);
    List<Request> findByStatus(String status);
    List<Request> findByCustomerId(Integer id);
}
