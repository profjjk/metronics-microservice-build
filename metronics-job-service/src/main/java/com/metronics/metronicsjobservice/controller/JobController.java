package com.metronics.metronicsjobservice.controller;

import com.metronics.metronicsjobservice.dto.Job;
import com.metronics.metronicsjobservice.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    JobRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Job createJob(@RequestBody Job job) {
        return repo.save(job);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Job> getAllJobs() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Job getJobById(@PathVariable int id) {
        return repo.getById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateJob(@RequestBody Job job) {
        repo.save(job);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJobById(@PathVariable int id) {
        repo.deleteById(id);
    }
}
