package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "report")
public class Report implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Time time;
    @Column(name = "content")
    private String content;
    @Column(name = "appointment")
    private String appointment;
    @Column(name = "agent")
    private String agent;

    public Report(String type, String content, String appointment, String agent) {
        this.type = type;
        this.date = new java.sql.Date(new java.util.Date().getTime());
        this.time = new java.sql.Time(new java.util.Date().getTime());
        this.content = content;
        this.appointment = appointment;
        this.agent = agent;
    }

    public Report() {
        this.date = new java.sql.Date(new java.util.Date().getTime());
        this.time = new java.sql.Time(new java.util.Date().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
