package com.metronics.metronicsmainservice.service;

import com.metronics.metronicsmainservice.Feign.CustomerServiceClient;
import com.metronics.metronicsmainservice.Feign.RequestServiceClient;
import com.metronics.metronicsmainservice.ViewModels.RequestViewModel;
import com.metronics.metronicsmainservice.model.Customer;
import com.metronics.metronicsmainservice.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RequestServiceLayer {
    private final CustomerServiceClient customerServiceClient;
    private final RequestServiceClient requestServiceClient;

    @Autowired
    RequestServiceLayer(RequestServiceClient requestServiceClient, CustomerServiceClient customerServiceClient){
        this.customerServiceClient = customerServiceClient;
        this.requestServiceClient = requestServiceClient;
    }


    public RequestViewModel getRequestById(Integer id){
        Request inputRequest = requestServiceClient.getRequestById(id);
        return buildRequestViewModel(inputRequest);
    }

    public List<RequestViewModel> getAllRequests(){
        List<RequestViewModel> returnList = new ArrayList<>();
        List<Request> requestList = requestServiceClient.getAllRequests();

        for (Request currentElement : requestList) {
            returnList.add(buildRequestViewModel(currentElement));
        }
        return returnList;
    }

    public List<RequestViewModel> findByStatus(String status) {
        List<RequestViewModel> returnList = new ArrayList<>();
        List<Request> requestList = requestServiceClient.findByStatus(status);

        for (Request currentElement : requestList) {
            returnList.add(buildRequestViewModel(currentElement));
        }
        return returnList;
    }

    public RequestViewModel buildRequestViewModel(Request request){
        RequestViewModel newRequestViewModel = new RequestViewModel();

        newRequestViewModel.setId(request.getId());
        newRequestViewModel.setType(request.getType());
        newRequestViewModel.setStatus(request.getStatus());
        newRequestViewModel.setDateCompleted(request.getDateCompleted());
        newRequestViewModel.setProblemNotes(request.getProblemNotes());
        newRequestViewModel.setRepairNotes(request.getRepairNotes());
        newRequestViewModel.setInvoiceNumber(request.getInvoiceNumber());

        Customer associatedCustomer;
        if(request.getCustomerId() != null){
            try{
                associatedCustomer = customerServiceClient.getCustomerById(request.getCustomerId());
                newRequestViewModel.setCustomerId(request.getCustomerId());
            } catch(Exception exception){
                associatedCustomer = null;
            }
        } else {
            associatedCustomer = null;
        }

        newRequestViewModel.setCustomer(associatedCustomer);

        return newRequestViewModel;

    }
}
