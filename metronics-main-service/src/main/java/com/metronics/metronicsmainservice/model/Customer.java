package com.metronics.metronicsmainservice.model;

import java.util.Objects;

public class Customer {
    private Integer id;
    private String businessName;
    private String contactName;
    private String phone;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(businessName, customer.businessName) && Objects.equals(contactName, customer.contactName) && Objects.equals(phone, customer.phone) && Objects.equals(street1, customer.street1) && Objects.equals(street2, customer.street2) && Objects.equals(city, customer.city) && Objects.equals(state, customer.state) && Objects.equals(zipcode, customer.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, businessName, contactName, phone, street1, street2, city, state, zipcode);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", contactName='" + contactName + '\'' +
                ", phone='" + phone + '\'' +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
