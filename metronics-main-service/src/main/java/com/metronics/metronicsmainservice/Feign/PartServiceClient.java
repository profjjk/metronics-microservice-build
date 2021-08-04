package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Part;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "part-service")
public interface PartServiceClient {
    @RequestMapping(value = "/api/parts", method = RequestMethod.POST)
    public Part addPart(@RequestBody Part part);

    @RequestMapping(value = "/api/parts", method = RequestMethod.GET)
    public List<Part> getAllParts();

    @RequestMapping(value = "/api/parts/{id}", method = RequestMethod.GET)
    public Part getPartById(@PathVariable Integer id);

    @RequestMapping(value = "/api/parts", method = RequestMethod.PUT)
    public void updatePart(@RequestBody Part part);

    @RequestMapping(value = "/api/parts/{id}", method = RequestMethod.DELETE)
    public void deletePart(@PathVariable Integer id);
}
