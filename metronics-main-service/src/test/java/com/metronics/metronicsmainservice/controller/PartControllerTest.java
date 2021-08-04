package com.metronics.metronicsmainservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metronics.metronicsmainservice.Feign.PartServiceClient;
import com.metronics.metronicsmainservice.model.Part;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class PartControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DataSource dataSource;

    @MockBean
    PartServiceClient partServiceClient;

    private ObjectMapper mapper = new ObjectMapper();
    private Part partOne;
    private Part partTwo;
    private Part partThree;
    private Part newPart;
    private Part newPartWithId;
    private List<Part> partList;
    private List<Part> washerPartList;

    @Before
    public void setUp() throws Exception {
        partOne = new Part("78222","a filter", 7.88, 15.00, 3);
        partOne.setId(1);
        partTwo = new Part("46553","a washer", 0.88, 5.00, 20);
        partTwo.setId(2);
        partThree = new Part("89201","a coupler", 100.00, 200.00, 0);
        partThree.setId(3);
        newPart = new Part("11111","a widget", 2.00, 4.00, 3);
        newPartWithId = new Part("11111","a widget", 2.00, 4.00, 3);
        newPartWithId.setId(4);
        partList = new ArrayList<>();
        washerPartList = new ArrayList<>();
        washerPartList.add(partTwo);
        partList.add(partOne);
        partList.add(partTwo);
        partList.add(partThree);
        doReturn(partList).when(partServiceClient).getAllParts();
        doReturn(newPartWithId).when(partServiceClient).addPart(newPart);
//        doReturn(newPart).when(partServiceClient).save(newPart);
        doReturn(partOne).when(partServiceClient).addPart(partOne);
       doReturn(partTwo).when(partServiceClient).getPartById(2);
        doReturn(null).when(partServiceClient).getPartById(3);
    }
    @Test
    public void shouldReturnAllPartsAnd200() throws Exception{
        String outputJson = mapper.writeValueAsString(partList);
        mockMvc.perform(get("/api/part"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldReturnPartWithGivenIdAnd200() throws Exception{
        String outputJson = mapper.writeValueAsString(partTwo);
        mockMvc.perform(get("/api/part/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldReturnCreatedPartAndCreated() throws Exception{

        String inputJson = mapper.writeValueAsString(newPart);
        String outputJson = mapper.writeValueAsString(newPartWithId);

        mockMvc.perform(post("/api/part")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldReturnOkWhenPartUpdated() throws Exception{

        newPart.setId(1);
        String inputJson = mapper.writeValueAsString(newPart);

        mockMvc.perform(put("/api/part")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReturnNoContentWhenPartDeleted() throws Exception{
        mockMvc.perform(delete("/api/part/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}