package com.metronics.metronicsmainservice.controller;

import com.metronics.metronicsmainservice.Feign.PartServiceClient;
import com.metronics.metronicsmainservice.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
//@RefreshScope
public class PartController {

    @Autowired
    private final PartServiceClient partServiceClient;

    PartController(PartServiceClient partServiceClient){
        this.partServiceClient = partServiceClient;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Part> getAllParts(){
        return partServiceClient.getAllParts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Part getPartById(@PathVariable Integer id){
        return partServiceClient.getPartById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Part addPart(@RequestBody Part part){
        return partServiceClient.addPart(part);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePart(@RequestBody Part part){
        partServiceClient.updatePart(part);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePart(@PathVariable Integer id){
        partServiceClient.deletePart(id);
    }
}

