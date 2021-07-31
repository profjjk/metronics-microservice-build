package com.metronics.metronicspartservice.repository;

import com.metronics.metronicspartservice.model.Part;
import com.metronics.metronicspartservice.repository.PartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartRepositoryTest {

    @Autowired
    PartRepository partRepo;

    private Part partOne;
    private Part partTwo;
    private Part partThree;
    private List<Part> partList;

    @Before
    public void setup(){
        partRepo.deleteAll();
        partOne = new Part("78222","a filter", 7.88, 15.00, 3);
        partTwo = new Part("46553","a washer", 0.88, 5.00, 20);
        partThree = new Part("89201","a coupler", 100.00, 200.00, 0);


        partList = new ArrayList<>();
        partList.add(partOne);
        partList.add(partTwo);
        partList.add(partThree);

        partRepo.saveAll(partList);
    }

    //Test getting all parts
    @Test
    public void shouldGetAllParts(){
        assertEquals(partList, partRepo.findAll());
    }
    //Test getting part by id
    @Test
    public void shouldGetPartById(){
        assertEquals(Optional.of(partOne), partRepo.findById(partOne.getId()));
    }
    //Test search by part number
    @Test
    public void shouldFindPartsWithPartNumberContainingAStringGivenByUser(){
        List<Part> partialList = new ArrayList<>();
        List<Part> returnedParts;

        partialList.add(partTwo);
        returnedParts = partRepo.findByPartNumberContaining("46553");

        assertEquals(partialList, returnedParts);
    }
    //Test search by description
    @Test
    public void shouldFindPartsWithDescriptionContainingAGivenString(){

        List<Part> partialList = new ArrayList<>();
        List<Part> returnedParts;

        partialList.add(partOne);
        returnedParts = partRepo.findByDescriptionContaining("filt");

        assertEquals(partialList, returnedParts);
    }
    //Test removing a part
    @Test
    public void shouldDeleteAPartWhenGivenId(){

        List<Part> partialList = new ArrayList<>();
        partialList.add(partTwo);
        partialList.add(partThree);


        partRepo.deleteById(partOne.getId());
        List<Part> remainingParts = partRepo.findAll();

        assertEquals(partialList, remainingParts);
    }
    //Test updating a part
    @Test
    public void shouldUpdatePart(){
        Part newPart = new Part("89233","a bolt", 0.24, 1.00, 100);
        newPart.setId(partOne.getId());
        partList.set(0, newPart);
        partRepo.save(newPart);
        assertEquals(partList,partRepo.findAll());

    }
    //Test adding a part
    @Test
    public void shouldAddANewPart(){
        Part newPart = new Part("89233","a bolt", 0.24, 1.00, 100);
        partList.add(newPart);
        partRepo.save(newPart);
        assertEquals(partList,partRepo.findAll());

    }



}