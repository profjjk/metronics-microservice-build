package com.metronics.metronicspartservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metronics.metronicspartservice.controller.PartsController;
import com.metronics.metronicspartservice.model.Part;
import com.metronics.metronicspartservice.repository.PartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PartsController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class})
public class PartsControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PartRepository partRepo;

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
        doReturn(partList).when(partRepo).findAll();
        doReturn(washerPartList).when(partRepo).findByDescriptionContaining("washer");
        doReturn(washerPartList).when(partRepo).findByPartNumberContaining("4655");
        doReturn(newPartWithId).when(partRepo).save(newPart);
//        doReturn(newPart).when(partRepo).save(newPart);
        doReturn(partOne).when(partRepo).save(partOne);
        doReturn(Optional.of(partTwo)).when(partRepo).findById(2);
    }

    @Test
    public void shouldReturnAllPartsAnd200() throws Exception{
        String outputJson = mapper.writeValueAsString(partList);
        mockMvc.perform(get("/part"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldReturnPartWithGivenIdAnd200() throws Exception{
        String outputJson = mapper.writeValueAsString(partTwo);
        mockMvc.perform(get("/part/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldReturnCreatedPartAndCreated() throws Exception{

        String inputJson = mapper.writeValueAsString(newPart);
        String outputJson = mapper.writeValueAsString(newPartWithId);

        mockMvc.perform(post("/part")
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

        mockMvc.perform(put("/part")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReturnNoContentWhenPartDeleted() throws Exception{
        mockMvc.perform(delete("/part/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    public void shouldReturnPartsWithMatchingDescriptionAnd200() throws Exception{

        String outputJson = mapper.writeValueAsString(washerPartList);
        System.out.println(washerPartList);
        mockMvc.perform(get("/part?description=washer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
    @Test
    public void shouldReturnPartsWithMatchingPartNumberAnd200() throws Exception{

        String outputJson = mapper.writeValueAsString(washerPartList);
        System.out.println(washerPartList);
        mockMvc.perform(get("/part?partNumber=4655"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }
}