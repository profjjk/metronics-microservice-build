package com.metronics.requestservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metronics.requestservice.dto.Request;
import com.metronics.requestservice.repository.RequestRepository;
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
@WebMvcTest(RequestController.class)
public class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RequestRepository mockRepo;

    ObjectMapper mapper = new ObjectMapper();

    private String inputJson;
    private String outputJson;

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
        inputRequest.setInvoiceNumber("11234");
        inputJson = mapper.writeValueAsString(inputRequest);

        Request outputRequest = new Request();
        outputRequest.setCustomerId(1);
        outputRequest.setType("repair");
        outputRequest.setProblemNotes("High pressure error during recovery cycle.");
        outputRequest.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        outputRequest.setStatus("completed");
        outputRequest.setDateCompleted("2020-08-07");
        outputRequest.setInvoiceNumber("11234");
        outputJson = mapper.writeValueAsString(outputRequest);

        when(mockRepo.save(inputRequest)).thenReturn(outputRequest);
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