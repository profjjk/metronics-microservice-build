package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Part;
import com.metronics.metronicsmainservice.model.Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "request-service")
public interface RequestServiceClient {
    @RequestMapping(value = "/api/requests", method = RequestMethod.GET)
    public List<Request> getAllRequests();
    @RequestMapping(value = "/api/requests/{id}", method = RequestMethod.GET)
    public Request getRequestById(@PathVariable Integer id);
    @RequestMapping(value = "/api/requests/status/{status}", method = RequestMethod.GET)
    public List<Request> findByStatus(@PathVariable String status);
    @RequestMapping(value = "/api/requests", method = RequestMethod.PUT)
    public void updateRequest(@RequestBody Request request);
    @RequestMapping(value = "/api/requests", method = RequestMethod.POST)
    public Request addRequest(@RequestBody Request request);
    @RequestMapping(value = "/api/requests/{id}", method = RequestMethod.DELETE)
    public void deleteRequest(@PathVariable Integer id);

}
