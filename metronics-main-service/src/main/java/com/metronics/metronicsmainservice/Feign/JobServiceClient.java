package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "job-service")
public interface JobServiceClient {
    @RequestMapping(value = "/api/jobs", method = RequestMethod.POST)
    Job addJob(@RequestBody Job job);

    @RequestMapping(value = "/api/jobs", method = RequestMethod.GET)
    List<Job> getAllJobs();

    @RequestMapping(value = "/api/jobs/{id}", method = RequestMethod.GET)
    Job getJobById(@PathVariable Integer id);

    @RequestMapping(value = "/api/jobs", method = RequestMethod.PUT)
    void updateJob(@RequestBody Job job);

    @RequestMapping(value = "/api/jobs/{id}", method = RequestMethod.DELETE)
    void deleteJob(@PathVariable Integer id);
}
