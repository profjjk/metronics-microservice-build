package com.metronics.metronicsjobservice.repository;

import com.metronics.metronicsjobservice.dto.Job;
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
public class JobRepositoryTest {

    @Autowired
    JobRepository repo;

    private Job job;

    @Before
    public void setUp() {
        // ARRANGE
        repo.deleteAll();

        job.setCustomerId(1);
        job.setType("repair");
        job.setProblemNotes("High pressure error during recovery cycle.");
        job.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        job.setStatus("completed");
        job.setDateCompleted("2020-08-07");
        job.setInvoiceNumber("11234");

        job = repo.save(job);
    }

    @Test
    public void shouldReturnRequestByDateCompleted() {
        // ACT
        List<Job> result = repo.findByDateCompleted("2020-08-07");

        // ASSERT
        assertEquals(1, result.size());
        assertEquals(job, result.get(0));
    }

    @Test
    public void shouldReturnRequestByStatus() {
        // ACT
        List<Job> result = repo.findByStatus("completed");

        // ASSERT
        assertEquals(1, result.size());
        assertEquals(job, result.get(0));
    }

    @Test
    public void shouldReturnRequestByCustomerId() {
        // ACT
        List<Job> result = repo.findByCustomerId(1);

        // ASSERT
        assertEquals(1, result.size());
        assertEquals(job, result.get(0));
    }
}