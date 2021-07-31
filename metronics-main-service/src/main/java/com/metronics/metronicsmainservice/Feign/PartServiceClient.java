package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Part;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "part-service")
public interface PartServiceClient {
    @RequestMapping(value = "/part", method = RequestMethod.GET)
    public List<Part> getAllParts();
    @RequestMapping(value = "/part/{id}", method = RequestMethod.GET)
    public Part getPartById(@PathVariable Integer id);
    @RequestMapping(value = "/part", method = RequestMethod.PUT)
    public void updatePart(@RequestBody Part part);
    @RequestMapping(value = "/part", method = RequestMethod.POST)
    public Part addPart(@RequestBody Part part);
    @RequestMapping(value = "/part/{id}", method = RequestMethod.DELETE)
    public void deletePart(@PathVariable Integer id);

}
