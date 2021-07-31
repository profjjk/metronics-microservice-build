package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customer-service")
@RequestMapping("/api/customers")
public interface CustomerServiceClient {
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer);

    @GetMapping
    public List<Customer> getAllCustomers();

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id);

    @PutMapping
    public void updateCustomer(@RequestBody Customer customer);

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id);
}
