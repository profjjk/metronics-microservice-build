package com.metronics.metronicsmainservice.service;

import com.metronics.metronicsmainservice.Feign.CustomerServiceClient;
import com.metronics.metronicsmainservice.Feign.JobServiceClient;
import com.metronics.metronicsmainservice.ViewModels.JobViewModel;
import com.metronics.metronicsmainservice.model.Customer;
import com.metronics.metronicsmainservice.model.Job;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JobServiceLayerTest {

    @Autowired
    private JobServiceLayer jobServiceLayer;

    @MockBean
    private JobServiceClient jobServiceClient;

    @MockBean
    private CustomerServiceClient customerServiceClient;

    private Job inputJob;
    private JobViewModel outputViewModel;
    private List<JobViewModel> viewModelList;


    @Before
    public void setUp() throws Exception {
        inputJob = new Job();
        inputJob.setCustomerId(1);
        inputJob.setType("repair");
        inputJob.setProblemNotes("High pressure error during recovery cycle.");
        inputJob.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        inputJob.setStatus("completed");
        inputJob.setDateCompleted("2020-08-07");


        Customer customerInput = new Customer();
        customerInput.setCity("Montclair");
        customerInput.setState("New Jersey");
        customerInput.setStreet("Claremont");
        customerInput.setZipcode("07054");
        customerInput.setPhone("123-456-7890");
        customerInput.setContactName("Amanda");
        customerInput.setBusinessName("Cognizant");
        customerInput.setId(1);

        outputViewModel = new JobViewModel();
        outputViewModel.setCustomer(customerInput);
        outputViewModel.setType("repair");
        outputViewModel.setProblemNotes("High pressure error during recovery cycle.");
        outputViewModel.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        outputViewModel.setStatus("completed");
        outputViewModel.setDateCompleted("2020-08-07");

        List<Job> jobList = new ArrayList<>();
        jobList.add(inputJob);

        viewModelList = new ArrayList<>();
        viewModelList.add(outputViewModel);

        doReturn(customerInput).when(customerServiceClient).getCustomerById(1);
        doReturn(inputJob).when(jobServiceClient).getJobById(1);
        doReturn(jobList).when(jobServiceClient).getAllJobs();

    }

    @Test
    public void shouldReturnRequestViewModelFromWhenGivenARequest(){
        assertEquals(jobServiceLayer.buildJobViewModel(inputJob), outputViewModel);

    }

    @Test
    public void shouldReturnViewModeloFGivenInputBasedOnId(){
        assertEquals(jobServiceLayer.getJobById(1), outputViewModel);
    }

    @Test
    public void shouldReturnListOfAllViewModels(){
        assertEquals(jobServiceLayer.getAllJobs(), viewModelList);

    }


}