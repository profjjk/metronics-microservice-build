package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Job;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "job-service")
@RequestMapping("/api/jobs")
public interface JobServiceClient {
    @PostMapping
    public Job addJob(@RequestBody Job job);

    @GetMapping
    public List<Job> getAllJobs();

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Integer id);

    @GetMapping("/status/{status}")
    public List<Job> findByStatus(@PathVariable String status);

    @PutMapping
    public void updateJob(@RequestBody Job job);

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Integer id);
}
