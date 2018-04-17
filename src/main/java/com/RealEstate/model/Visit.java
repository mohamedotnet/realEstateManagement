package com.RealEstate.model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "visit")
public class Visit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "appointment")
    private String appointment;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Time time;
    @Column(name = "customer")
    private String customer;
    @Column(name = "agent")
    private String agent;

    public Visit(String appointment, String customer, String agent) {
        this.appointment = appointment;
        this.date = new java.sql.Date(new java.util.Date().getTime());
        this.time = new java.sql.Time(new java.util.Date().getTime());
        this.customer = customer;
        this.agent = agent;
    }

    public Visit() {
        this.date = new java.sql.Date(new java.util.Date().getTime());
        this.time = new java.sql.Time(new java.util.Date().getTime());
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

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }
}
