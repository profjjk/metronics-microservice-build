package com.metronics.metronicsmainservice.repository;

import com.metronics.metronicsmainservice.dto.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
}
