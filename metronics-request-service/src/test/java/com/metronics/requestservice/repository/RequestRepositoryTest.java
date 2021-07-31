package com.metronics.requestservice.repository;

import com.metronics.requestservice.dto.Request;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestRepositoryTest {

    @Autowired
    RequestRepository repo;

    private Request request;

    @Before
    public void setUp() {
        // ARRANGE
        repo.deleteAll();

        request.setCustomerId(1);
        request.setType("repair");
        request.setProblemNotes("High pressure error during recovery cycle.");
        request.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        request.setStatus("completed");
        request.setDateCompleted("2020-08-07");
        request.setInvoiceNumber("11234");

        request = repo.save(request);
    }

    @Test
    public void shouldReturnRequestByDateCompleted() {
        // ACT
        List<Request> result = repo.findByDateCompleted("2020-08-07");

        // ASSERT
        assertEquals(1, result.size());
        assertEquals(request, result.get(0));
    }

    @Test
    public void shouldReturnRequestByStatus() {
        // ACT
        List<Request> result = repo.findByStatus("completed");

        // ASSERT
        assertEquals(1, result.size());
        assertEquals(request, result.get(0));
    }

    @Test
    public void shouldReturnRequestByCustomerId() {
        // ACT
        List<Request> result = repo.findByCustomerId(1);

        // ASSERT
        assertEquals(1, result.size());
        assertEquals(request, result.get(0));
    }
}