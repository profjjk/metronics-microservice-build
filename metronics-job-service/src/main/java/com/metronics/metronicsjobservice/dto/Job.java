package com.metronics.metronicsjobservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "jobs")
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer customerId;
    private String type;
    private String status;
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
        Job job = (Job) o;
        return Objects.equals(id, job.id) && Objects.equals(customerId, job.customerId) && Objects.equals(type, job.type) && Objects.equals(status, job.status) && Objects.equals(dateCompleted, job.dateCompleted) && Objects.equals(problemNotes, job.problemNotes) && Objects.equals(repairNotes, job.repairNotes) && Objects.equals(invoiceNumber, job.invoiceNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, type, status, dateCompleted, problemNotes, repairNotes, invoiceNumber);
    }

    @Override
    public String toString() {
        return "Job{" +
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
