package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;


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
    @Column(name = "agent")
    private String agent;
    @Column(name = "locality")
    private String locality;

    public Appointment(String reference, Date date, Time time, String customer, String locality, String agent) {
        this.reference = reference;
        this.date = date;
        this.time = time;
        this.customer = customer;
        this.agent = agent;
        this.locality = locality;
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

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}