package com.metronics.metronicsmainservice.controller;

import com.metronics.metronicsmainservice.Feign.PartServiceClient;
import com.metronics.metronicsmainservice.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/part")
//@RefreshScope
public class PartController {

    @Autowired
    private final PartServiceClient partServiceClient;

    PartController(PartServiceClient partServiceClient){
        this.partServiceClient = partServiceClient;
    }

    @RequestMapping( method = RequestMethod.GET)
    public List<Part> getAllParts(){
            return partServiceClient.getAllParts();
    }
    @RequestMapping( value= "/{id}", method = RequestMethod.GET)
    public Part getPartByID(@PathVariable Integer id){
        return partServiceClient.getPartById(id);
    }
    @RequestMapping( value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Part addPart(@RequestBody Part part){
        return partServiceClient.addPart(part);
    }
    @RequestMapping( value = "", method = RequestMethod.PUT)
    public void updatePart(@RequestBody Part part){
        partServiceClient.updatePart(part);
    }
    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePart(@PathVariable Integer id){
        partServiceClient.deletePart(id);
    }



}

