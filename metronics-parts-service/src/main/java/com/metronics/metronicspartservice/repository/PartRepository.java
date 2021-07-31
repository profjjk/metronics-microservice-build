package com.metronics.metronicspartservice.repository;

import com.metronics.metronicspartservice.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part,Integer> {
    List<Part> findByPartNumberContaining(String partNumber);
    List<Part> findByDescriptionContaining(String partialDescription);
}
