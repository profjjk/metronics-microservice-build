package com.metronics.metronicsmainservice.ViewModels;

import com.metronics.metronicsmainservice.model.Customer;

import java.util.Objects;

public class RequestViewModel {
    private Integer id;
    private Customer customer;
    private String type;            // Maintenance, Repair, Training, Callback, Unknown(default)
    private String status;          // Waiting(default), Scheduled, Completed, Canceled
    private String dateCompleted;
    private String problemNotes;
    private String repairNotes;
    private String invoiceNumber;
    private Integer customerId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getProblemNotes() {
        return problemNotes;
    }

    public void setProblemNotes(String problemNotes) {
        this.problemNotes = problemNotes;
    }

    public String getRepairNotes() {
        return repairNotes;
    }

    public void setRepairNotes(String repairNotes) {
        this.repairNotes = repairNotes;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestViewModel that = (RequestViewModel) o;
        return Objects.equals(id, that.id) && Objects.equals(customer, that.customer) && Objects.equals(type, that.type) && Objects.equals(status, that.status) && Objects.equals(dateCompleted, that.dateCompleted) && Objects.equals(problemNotes, that.problemNotes) && Objects.equals(repairNotes, that.repairNotes) && Objects.equals(invoiceNumber, that.invoiceNumber) && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, type, status, dateCompleted, problemNotes, repairNotes, invoiceNumber, customerId);
    }

    @Override
    public String toString() {
        return "RequestViewModel{" +
                "id=" + id +
                ", customer=" + customer +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", dateCompleted='" + dateCompleted + '\'' +
                ", problemNotes='" + problemNotes + '\'' +
                ", repairNotes='" + repairNotes + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
