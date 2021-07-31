package com.metronics.metronicspartservice.controller;


import com.metronics.metronicspartservice.model.Part;
import com.metronics.metronicspartservice.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PartsController {

    @Autowired
    PartRepository partRepo;
    //get all and search
    @GetMapping("/part")
    public List<Part> getAllParts(@RequestParam(required = false) String description, @RequestParam(required = false) String partNumber){
        if(description != null){
            return partRepo.findByDescriptionContaining(description);
        } else if(partNumber != null){
           return partRepo.findByPartNumberContaining(partNumber);
        }
        return partRepo.findAll();
    }
    //get by id
    @GetMapping("/part/{id}")
    public Optional<Part> getAllParts(@PathVariable int id){
        return partRepo.findById(id);
    }

    //new part
    @PostMapping("/part")
    @ResponseStatus(HttpStatus.CREATED)
    public Part saveNewPart(@RequestBody Part part){
        return partRepo.save(part);
    }
    //update part
    @PutMapping("/part")
    public void updatePart(@RequestBody Part part){
        partRepo.save(part);
    }
    //delete part
    @DeleteMapping("/part/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePart(@PathVariable int id){
        partRepo.deleteById(id);
    }
}
