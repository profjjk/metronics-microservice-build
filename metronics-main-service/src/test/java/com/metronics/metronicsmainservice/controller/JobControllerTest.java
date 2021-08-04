package com.metronics.metronicsmainservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metronics.metronicsmainservice.Feign.JobServiceClient;
import com.metronics.metronicsmainservice.ViewModels.JobViewModel;
import com.metronics.metronicsmainservice.model.Job;
import com.metronics.metronicsmainservice.service.JobServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class JobControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    JobServiceClient jobServiceClient;

    @MockBean
    DataSource dataSource;

    @MockBean
    JobServiceLayer jobServiceLayer;

    ObjectMapper mapper = new ObjectMapper();

    private String inputJson;
    private String outputJson;
    private String outputViewModelJson;
    private List<Job> jobList = new ArrayList<>();

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
        inputJson = mapper.writeValueAsString(inputJob);

        Job outputJob = new Job();
        outputJob.setCustomerId(1);
        outputJob.setType("repair");
        outputJob.setProblemNotes("High pressure error during recovery cycle.");
        outputJob.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        outputJob.setStatus("completed");
        outputJob.setDateCompleted("2020-08-07");
        outputJson = mapper.writeValueAsString(outputJob);

        jobList.add(inputJob);

        JobViewModel outputViewModel = new JobViewModel();
        outputViewModel.setCustomer(null);
        outputViewModel.setType("repair");
        outputViewModel.setProblemNotes("High pressure error during recovery cycle.");
        outputViewModel.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        outputViewModel.setStatus("completed");
        outputViewModel.setDateCompleted("2020-08-07");
        outputViewModelJson = mapper.writeValueAsString(outputViewModel);


        when(jobServiceClient.addJob(inputJob)).thenReturn(outputJob);
        when(jobServiceClient.getJobById(1)).thenReturn(outputJob);
        when(jobServiceClient.getAllJobs()).thenReturn(jobList);
        when(jobServiceLayer.getJobById(1)).thenReturn(outputViewModel);
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
                .andExpect(status().isOk())
                .andExpect(content().json(outputViewModelJson));
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