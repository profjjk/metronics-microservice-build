package com.metronics.metronicsmainservice.repository;

import com.metronics.metronicsmainservice.dto.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {


}
