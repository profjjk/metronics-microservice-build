package com.metronics.metronicsmainservice.controller;

import com.metronics.metronicsmainservice.Feign.JobServiceClient;
import com.metronics.metronicsmainservice.ViewModels.JobViewModel;
import com.metronics.metronicsmainservice.model.Job;
import com.metronics.metronicsmainservice.service.JobServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private final JobServiceClient jobServiceClient;

    @Autowired
    private JobServiceLayer jobServiceLayer;

    JobController(JobServiceClient jobServiceClient){
        this.jobServiceClient = jobServiceClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job addJob(@RequestBody Job job){
        return jobServiceClient.addJob(job);
    }

    @GetMapping
    public List<JobViewModel> getAllJobs(){
        return jobServiceLayer.getAllJobs();
//        return jobServiceClient.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobViewModel getJobById(@PathVariable int id){
        return jobServiceLayer.getJobById(id);
//        return jobServiceClient.getJobById(id);
    }

    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<JobViewModel> getJobById(@PathVariable String status) {
        return jobServiceLayer.findByStatus(status);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateJob(@RequestBody Job job){
        jobServiceClient.updateJob(job);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable Integer id){
        jobServiceClient.deleteJob(id);
    }

}
