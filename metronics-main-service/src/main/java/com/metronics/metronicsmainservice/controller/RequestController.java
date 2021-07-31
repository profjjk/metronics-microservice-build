package com.metronics.metronicsmainservice.controller;

import com.metronics.metronicsmainservice.Feign.RequestServiceClient;
import com.metronics.metronicsmainservice.ViewModels.RequestViewModel;
import com.metronics.metronicsmainservice.model.Part;
import com.metronics.metronicsmainservice.model.Request;
import com.metronics.metronicsmainservice.service.RequestServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    @Autowired
    private final RequestServiceClient requestServiceClient;

    @Autowired
            private RequestServiceLayer requestServiceLayer;

    RequestController(RequestServiceClient requestServiceClient){

        this.requestServiceClient = requestServiceClient;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<RequestViewModel> getAllRequests(){
        return requestServiceLayer.getAllRequests();
//        return requestServiceClient.getAllRequests();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RequestViewModel getRequestById(@PathVariable int id){
        return requestServiceLayer.getRequestById(id);
//        return requestServiceClient.getRequestById(id);
    }
    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestViewModel> getRequestById(@PathVariable String status) {
        return requestServiceLayer.findByStatus(status);
    }
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRequest(@RequestBody Request request){
        requestServiceClient.updateRequest(request);
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Request addRequest(@RequestBody Request request){
        return requestServiceClient.addRequest(request);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequest(@PathVariable Integer id){
        requestServiceClient.deleteRequest(id);
    }

}
