package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "payment_receipt")
public class PaymentReceipt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "value")
    private int value;
    @Column(name = "bank")
    private String bank;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Time time;
    @Column(name = "customer")
    private String customer;
    @Column(name = "picture")
    private String picture;

    public PaymentReceipt(int value, String bank, String customer, String picture) {
        this.value = value;
        this.bank = bank;
        this.date = new java.sql.Date(new java.util.Date().getTime());
        this.time = new java.sql.Time(new java.util.Date().getTime());
        this.customer = customer;
        this.picture = picture;
    }

    public PaymentReceipt() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
