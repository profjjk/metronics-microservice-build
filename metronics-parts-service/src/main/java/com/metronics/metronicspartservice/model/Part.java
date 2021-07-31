package com.metronics.metronicspartservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "part")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String partNumber;
    private String description;
    private double purchasePrice;
    private double salePrice;
    private int quantity;

    public Part(String partNumber, String description, double purchasePrice, double salePrice, int quantity) {
        this.partNumber = partNumber;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
    }
    public Part(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Double.compare(part.purchasePrice, purchasePrice) == 0 && Double.compare(part.salePrice, salePrice) == 0 && quantity == part.quantity && Objects.equals(id, part.id) && Objects.equals(partNumber, part.partNumber) && Objects.equals(description, part.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partNumber, description, purchasePrice, salePrice, quantity);
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", partNumber='" + partNumber + '\'' +
                ", description='" + description + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", quantity=" + quantity +
                '}';
    }
}
