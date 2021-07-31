package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "metronics-customer-service")
public interface CustomerServiceClient {
    @RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomers();
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable Integer id);
    @RequestMapping(value = "/api/customer", method = RequestMethod.PUT)
    public void updateCustomer(@RequestBody Customer customer, @PathVariable Integer id);
    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer customer);
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Integer id);
}
