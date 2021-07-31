package com.metronics.metronicsmainservice.controller;

import com.metronics.metronicsmainservice.ViewModels.JobViewModel;
import com.metronics.metronicsmainservice.service.JobServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ViewModelController {

    @Autowired
    private JobServiceLayer serviceLayer;

    @GetMapping("/viewmodel")
    @ResponseStatus(HttpStatus.OK)
    public List<JobViewModel> getViewModel() {
        return serviceLayer.getAllJobs();
    }
}
