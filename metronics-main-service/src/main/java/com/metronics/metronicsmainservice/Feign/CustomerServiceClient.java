package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {
    @RequestMapping(value = "/api/customers", method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody Customer customer);

    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers();

    @RequestMapping(value = "/api/customers/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable Integer id);

    @RequestMapping(value = "/api/customers", method = RequestMethod.PUT)
    public void updateCustomer(@RequestBody Customer customer);

    @RequestMapping(value = "/api/customers/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Integer id);
}
