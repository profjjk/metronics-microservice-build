package com.metronics.requestservice.controller;

import com.metronics.requestservice.dto.Request;
import com.metronics.requestservice.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    RequestRepository repo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Request createRequest(@RequestBody Request request) {
        return repo.save(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Request> getAllRequests() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Request getRequestById(@PathVariable int id) {
        return repo.getById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRequest(@RequestBody Request request) {
        repo.save(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequestById(@PathVariable int id) {
        repo.deleteById(id);
    }
}
