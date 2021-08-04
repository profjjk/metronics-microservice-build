package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "job-service")
public interface JobServiceClient {
    @RequestMapping(value = "/api/jobs", method = RequestMethod.POST)
    public Job addJob(@RequestBody Job job);

    @RequestMapping(value = "/api/jobs", method = RequestMethod.GET)
    public List<Job> getAllJobs();

    @RequestMapping(value = "/api/jobs/{id}", method = RequestMethod.GET)
    public Job getJobById(@PathVariable Integer id);

    @RequestMapping(value = "/api/jobs/status/{status}", method = RequestMethod.GET)
    public List<Job> findByStatus(@PathVariable String status);

    @RequestMapping(value = "/api/jobs", method = RequestMethod.PUT)
    public void updateJob(@RequestBody Job job);

    @RequestMapping(value = "/api/jobs/{id}", method = RequestMethod.DELETE)
    public void deleteJob(@PathVariable Integer id);
}
