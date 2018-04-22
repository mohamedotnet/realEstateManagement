package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table (name = "appointment")
public class Appointment implements Serializable {

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
    @Column(name = "appartment_ref")
    private String appartment;
    @Column(name = "status")
    private boolean status;

    public Appointment(Date date, String reference, Time time, String customer, String appartment) {
        this.date = date;
        this.reference = reference;
        this.time = time;
        this.customer = customer;
        this.appartment = appartment;
        this.status = false;
    }

    public Appointment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}