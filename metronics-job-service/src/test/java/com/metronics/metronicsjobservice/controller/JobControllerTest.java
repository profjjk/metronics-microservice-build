package com.metronics.metronicsjobservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metronics.metronicsjobservice.dto.Job;
import com.metronics.metronicsjobservice.repository.JobRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(JobController.class)
public class JobControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JobRepository mockRepo;

    ObjectMapper mapper = new ObjectMapper();

    private String inputJson;
    private String outputJson;

    @Before
    public void setUp() throws Exception {
        // ARRANGE
        Job inputJob = new Job();
        inputJob.setCustomerId(1);
        inputJob.setType("repair");
        inputJob.setProblemNotes("High pressure error during recovery cycle.");
        inputJob.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        inputJob.setStatus("completed");
        inputJob.setDateCompleted("2020-08-07");
        inputJob.setInvoiceNumber("11234");
        inputJson = mapper.writeValueAsString(inputJob);

        Job outputJob = new Job();
        outputJob.setCustomerId(1);
        outputJob.setType("repair");
        outputJob.setProblemNotes("High pressure error during recovery cycle.");
        outputJob.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        outputJob.setStatus("completed");
        outputJob.setDateCompleted("2020-08-07");
        outputJob.setInvoiceNumber("11234");
        outputJson = mapper.writeValueAsString(outputJob);

        when(mockRepo.save(inputJob)).thenReturn(outputJob);
    }

    @Test
    public void shouldReturnNewRequestOnPost() throws Exception {
        // ACT
        mockMvc.perform(post("/api/requests").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                // ASSERT
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnAllRequests() throws Exception {
        // ACT
        mockMvc.perform(get("/api/requests"))
                // ASSERT
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void shouldReturnRequestById() throws Exception {
        // ACT
        mockMvc.perform(get("/api/requests/1"))
                // ASSERT
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateRequest() throws Exception {
        // ACT
        mockMvc.perform(put("/api/requests").content(inputJson).contentType(MediaType.APPLICATION_JSON))
                // ASSERT
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteRequestById() throws Exception {
        // ACT
        mockMvc.perform(delete("/api/requests/1"))
                // ASSERT
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}