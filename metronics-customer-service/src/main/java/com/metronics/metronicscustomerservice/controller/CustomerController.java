package com.metronics.metronicscustomerservice.controller;

import com.metronics.metronicscustomerservice.dto.Customer;
import com.metronics.metronicscustomerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping(value = "/api/customer")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;

    }


    @GetMapping(value = "/api/customer")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping(value = "/api/customer/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Customer findCustomerById(@PathVariable int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new RuntimeException("No customer associated with id: " + id);

    }

    @PutMapping(value = "/api/customer/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCustomerById(@RequestBody Customer customer, @PathVariable int id) {
        if (customer.getId() != id) {
            throw new IllegalArgumentException();
        } else {
            customerRepository.save(customer);
        }
    }

    @DeleteMapping(value = "/api/customer/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCustomerById(Customer customer, @PathVariable int id) {
        if (customer.getId() != id) {
            throw new IllegalArgumentException();
        } else {
            customerRepository.deleteById(id);
        }
    }

}
