package com.metronics.metronicscustomerservice.repository;

import com.metronics.metronicscustomerservice.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
