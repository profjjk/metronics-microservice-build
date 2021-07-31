package com.metronics.metronicspartservice.controller;


import com.metronics.metronicspartservice.model.Part;
import com.metronics.metronicspartservice.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parts")
public class PartsController {

    @Autowired
    PartRepository repo;

    @GetMapping
    public List<Part> getAllParts(@RequestParam(required = false) String description, @RequestParam(required = false) String partNumber){
        if(description != null){
            return repo.findByDescriptionContaining(description);
        } else if(partNumber != null){
           return repo.findByPartNumberContaining(partNumber);
        }
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Part> getAllParts(@PathVariable int id){
        return repo.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Part createPart(@RequestBody Part part){
        return repo.save(part);
    }

    @PutMapping
    public void updatePart(@RequestBody Part part){
        repo.save(part);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePart(@PathVariable int id){
        repo.deleteById(id);
    }
}
