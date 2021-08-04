package com.metronics.metronicscustomerservice.controller;

import com.metronics.metronicscustomerservice.dto.Customer;
import com.metronics.metronicscustomerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> findAllCustomers() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Customer findCustomerById(@PathVariable int id) {
        return repo.getById(id);

    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer) {
        repo.save(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCustomerById(Customer customer, @PathVariable int id) {
        repo.deleteById(id);
    }

}
