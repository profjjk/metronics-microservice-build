package com.metronics.metronicsmainservice.controller;

import com.metronics.metronicsmainservice.Feign.CustomerServiceClient;
import com.metronics.metronicsmainservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerServiceClient customerServiceClient;

    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerServiceClient.addCustomer(customer);
    }
    @GetMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> findAllCustomers() {
        return customerServiceClient.getAllCustomers();
    }

    @GetMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Customer findCustomerById(@PathVariable int id) {
        return customerServiceClient.getCustomerById(id);
    }

    @PutMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCustomerById(@RequestBody Customer customer, @PathVariable int id) {
        customerServiceClient.updateCustomer(customer, id);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCustomerById(Customer customer, @PathVariable int id) {
        if (customer.getId() != id) {
            throw new IllegalArgumentException();
        } else {
            customerServiceClient.deleteCustomer(id);
        }
    }


}
