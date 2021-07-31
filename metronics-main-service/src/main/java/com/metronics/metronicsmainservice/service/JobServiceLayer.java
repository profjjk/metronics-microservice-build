package com.metronics.metronicsmainservice.service;

import com.metronics.metronicsmainservice.Feign.CustomerServiceClient;
import com.metronics.metronicsmainservice.Feign.JobServiceClient;
import com.metronics.metronicsmainservice.ViewModels.JobViewModel;
import com.metronics.metronicsmainservice.model.Customer;
import com.metronics.metronicsmainservice.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobServiceLayer {
    private final CustomerServiceClient customerServiceClient;
    private final JobServiceClient jobServiceClient;

    @Autowired
    JobServiceLayer(JobServiceClient jobServiceClient, CustomerServiceClient customerServiceClient){
        this.customerServiceClient = customerServiceClient;
        this.jobServiceClient = jobServiceClient;
    }


    public JobViewModel getJobById(Integer id){
        Job inputJob = jobServiceClient.getJobById(id);
        return buildJobViewModel(inputJob);
    }

    public List<JobViewModel> getAllJobs(){
        List<JobViewModel> returnList = new ArrayList<>();
        List<Job> jobList = jobServiceClient.getAllJobs();

        for (Job currentElement : jobList) {
            returnList.add(buildJobViewModel(currentElement));
        }
        return returnList;
    }

    public List<JobViewModel> findByStatus(String status) {
        List<JobViewModel> returnList = new ArrayList<>();
        List<Job> jobList = jobServiceClient.findByStatus(status);

        for (Job currentElement : jobList) {
            returnList.add(buildJobViewModel(currentElement));
        }
        return returnList;
    }

    public JobViewModel buildJobViewModel(Job job){
        JobViewModel newJobViewModel = new JobViewModel();

        newJobViewModel.setId(job.getId());
        newJobViewModel.setType(job.getType());
        newJobViewModel.setStatus(job.getStatus());
        newJobViewModel.setDateCompleted(job.getDateCompleted());
        newJobViewModel.setProblemNotes(job.getProblemNotes());
        newJobViewModel.setRepairNotes(job.getRepairNotes());
        newJobViewModel.setInvoiceNumber(job.getInvoiceNumber());

        Customer associatedCustomer;
        if(job.getCustomerId() != null){
            try{
                associatedCustomer = customerServiceClient.getCustomerById(job.getCustomerId());
                newJobViewModel.setCustomerId(job.getCustomerId());
            } catch(Exception exception){
                associatedCustomer = null;
            }
        } else {
            associatedCustomer = null;
        }

        newJobViewModel.setCustomer(associatedCustomer);

        return newJobViewModel;
    }
}
