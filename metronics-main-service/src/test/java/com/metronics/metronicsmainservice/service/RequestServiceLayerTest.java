package com.metronics.metronicsmainservice.service;

import com.metronics.metronicsmainservice.Feign.CustomerServiceClient;
import com.metronics.metronicsmainservice.Feign.RequestServiceClient;
import com.metronics.metronicsmainservice.ViewModels.RequestViewModel;
import com.metronics.metronicsmainservice.model.Customer;
import com.metronics.metronicsmainservice.model.Request;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RequestServiceLayerTest {

    @Autowired
    private RequestServiceLayer requestServiceLayer;

    @MockBean
    private RequestServiceClient requestServiceClient;

    @MockBean
    private CustomerServiceClient customerServiceClient;

    private Request inputRequest;
    private RequestViewModel outputViewModel;
    private List<RequestViewModel> viewModelList;


    @Before
    public void setUp() throws Exception {
        inputRequest = new Request();
        inputRequest.setCustomerId(1);
        inputRequest.setType("repair");
        inputRequest.setProblemNotes("High pressure error during recovery cycle.");
        inputRequest.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        inputRequest.setStatus("completed");
        inputRequest.setDateCompleted("2020-08-07");


        Customer customerInput = new Customer();
        customerInput.setCity("Montclair");
        customerInput.setState("New Jersey");
        customerInput.setStreet("Claremont");
        customerInput.setZipcode("07054");
        customerInput.setPhone("123-456-7890");
        customerInput.setContactName("Amanda");
        customerInput.setBusinessName("Cognizant");
        customerInput.setId(1);

        outputViewModel = new RequestViewModel();
        outputViewModel.setCustomer(customerInput);
        outputViewModel.setType("repair");
        outputViewModel.setProblemNotes("High pressure error during recovery cycle.");
        outputViewModel.setRepairNotes("Replaced recycle solenoid, changed filter & oil, calibrated scale.");
        outputViewModel.setStatus("completed");
        outputViewModel.setDateCompleted("2020-08-07");

        List<Request> requestList = new ArrayList<>();
        requestList.add(inputRequest);

        viewModelList = new ArrayList<>();
        viewModelList.add(outputViewModel);

        doReturn(customerInput).when(customerServiceClient).getCustomerById(1);
        doReturn(inputRequest).when(requestServiceClient).getRequestById(1);
        doReturn(requestList).when(requestServiceClient).getAllRequests();

    }

    @Test
    public void shouldReturnRequestViewModelFromWhenGivenARequest(){
        assertEquals(requestServiceLayer.buildRequestViewModel(inputRequest), outputViewModel);

    }

    @Test
    public void shouldReturnViewModeloFGivenInputBasedOnId(){
        assertEquals(requestServiceLayer.getRequestById(1), outputViewModel);
    }

    @Test
    public void shouldReturnListOfAllViewModels(){
        assertEquals(requestServiceLayer.getAllRequests(), viewModelList);

    }


}