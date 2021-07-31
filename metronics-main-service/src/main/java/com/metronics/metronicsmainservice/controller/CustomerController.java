package com.metronics.metronicsmainservice.controller;

import com.metronics.metronicsmainservice.Feign.CustomerServiceClient;
import com.metronics.metronicsmainservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerServiceClient customerServiceClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerServiceClient.createCustomer(customer);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAllCustomers() {
        return customerServiceClient.getAllCustomers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findCustomerById(@PathVariable int id) {
        return customerServiceClient.getCustomerById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer) {
        customerServiceClient.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(Customer customer, @PathVariable int id) {
        customerServiceClient.deleteCustomer(id);
    }


}
