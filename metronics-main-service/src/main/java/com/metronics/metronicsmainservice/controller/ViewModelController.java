package com.metronics.metronicsmainservice.controller;

import com.metronics.metronicsmainservice.ViewModels.RequestViewModel;
import com.metronics.metronicsmainservice.service.RequestServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewModelController {

    @Autowired
    private RequestServiceLayer serviceLayer;

    @GetMapping(value = "/viewmodel")
    @ResponseStatus(value = HttpStatus.OK)
    public List<RequestViewModel> getViewModel() {
        return serviceLayer.getAllRequests();
    }
}
