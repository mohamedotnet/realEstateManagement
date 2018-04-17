package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "sale")
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "reference")
    private String reference;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Time time;
    @Column(name = "customer")
    private String customer;
    @Column(name = "id_apartment")
    private int apartment;
    @Column(name = "is_validated")
    private boolean isValidated;
    @Column(name = "id_payment_receipt")
    private int paymentReceipt;

    public Sale(String reference, String customer, int apartment, boolean isValidated, int paymentReceipt) {
        this.reference = reference;
        this.date = new java.sql.Date(new java.util.Date().getTime());
        this.time = new java.sql.Time(new java.util.Date().getTime());
        this.customer = customer;
        this.apartment = apartment;
        this.isValidated = isValidated;
        this.paymentReceipt = paymentReceipt;
    }

    public Sale() {
        this.paymentReceipt = 1;
        this.date = new java.sql.Date(new java.util.Date().getTime());
        this.time = new java.sql.Time(new java.util.Date().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
        this.apartment = apartment;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }

    public int getPaymentReceipt() {
        return paymentReceipt;
    }

    public void setPaymentReceipt(int paymentReceipt) {
        this.paymentReceipt = paymentReceipt;
    }
}
