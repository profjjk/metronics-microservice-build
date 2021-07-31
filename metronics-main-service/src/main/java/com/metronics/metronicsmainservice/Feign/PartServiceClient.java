package com.metronics.metronicsmainservice.Feign;

import com.metronics.metronicsmainservice.model.Part;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "part-service")
@RequestMapping("/api/parts")
public interface PartServiceClient {
    @PostMapping
    public Part addPart(@RequestBody Part part);

    @GetMapping
    public List<Part> getAllParts();

    @GetMapping("/{id}")
    public Part getPartById(@PathVariable Integer id);

    @PutMapping
    public void updatePart(@RequestBody Part part);

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Integer id);

}
