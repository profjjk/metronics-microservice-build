package com.metronics.requestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name="requests")
public class Request implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer customerId;
    private String type;            // Maintenance, Repair, Training, Callback, Unknown(default)
    private String status;          // Waiting(default), Scheduled, Completed, Canceled
    private String dateCompleted;
    @Lob
    private String problemNotes;
    @Lob
    private String repairNotes;
    private String invoiceNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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
        Request request = (Request) o;
        return Objects.equals(id, request.id) && Objects.equals(customerId, request.customerId) && Objects.equals(type, request.type) && Objects.equals(status, request.status) && Objects.equals(dateCompleted, request.dateCompleted) && Objects.equals(problemNotes, request.problemNotes) && Objects.equals(repairNotes, request.repairNotes) && Objects.equals(invoiceNumber, request.invoiceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, type, status, dateCompleted, problemNotes, repairNotes, invoiceNumber);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", dateCompleted='" + dateCompleted + '\'' +
                ", problemNotes='" + problemNotes + '\'' +
                ", repairNotes='" + repairNotes + '\'' +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                '}';
    }
}
