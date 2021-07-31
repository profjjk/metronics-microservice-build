package com.metronics.metronicsmainservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metronics.metronicsmainservice.Feign.RequestServiceClient;
import com.metronics.metronicsmainservice.ViewModels.RequestViewModel;
import com.metronics.metronicsmainservice.model.Request;
import com.metronics.metronicsmainservice.service.RequestServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RequestController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class})
@AutoConfigureMockMvc(addFilters = false)
public class RequestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    RequestServiceClient requestServiceClient;

    @MockBean
    DataSource dataSource;

    @MockBean
    RequestServiceLayer requestServiceLayer;

    ObjectMapper mapper = new ObjectMapper();

    private String inputJson;
    private String outputJson;
    private String outputViewModelJson;
    private List<Request> requestList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        // ARRANGE
        Request inputRequest = new Request();
        inputRequest.setCustomerId(1);
        inputRequest.setType("repair");
        inputRequest.setProblemNotes("High pressure error during recovery cycle.");
        inputRequest.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        inputRequest.setStatus("completed");
        inputRequest.setDateCompleted("2020-08-07");
        inputJson = mapper.writeValueAsString(inputRequest);

        Request outputRequest = new Request();
        outputRequest.setCustomerId(1);
        outputRequest.setType("repair");
        outputRequest.setProblemNotes("High pressure error during recovery cycle.");
        outputRequest.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        outputRequest.setStatus("completed");
        outputRequest.setDateCompleted("2020-08-07");
        outputJson = mapper.writeValueAsString(outputRequest);

        requestList.add(inputRequest);

        RequestViewModel outputViewModel = new RequestViewModel();
        outputViewModel.setCustomer(null);
        outputViewModel.setType("repair");
        outputViewModel.setProblemNotes("High pressure error during recovery cycle.");
        outputViewModel.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        outputViewModel.setStatus("completed");
        outputViewModel.setDateCompleted("2020-08-07");
        outputViewModelJson = mapper.writeValueAsString(outputViewModel);


        when(requestServiceClient.addRequest(inputRequest)).thenReturn(outputRequest);
        when(requestServiceClient.getRequestById(1)).thenReturn(outputRequest);
        when(requestServiceClient.getAllRequests()).thenReturn(requestList);
        when(requestServiceLayer.getRequestById(1)).thenReturn(outputViewModel);
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