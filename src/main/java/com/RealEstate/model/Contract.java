package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "contract")
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Time time;
    @Column(name = "sale")
    private String sale;
    @Column(name = "manager")
    private String manager;
    @Column(name = "content")
    private String content;
    @Column(name = "file")
    private String file;

    public Contract(Date date, Time time, String sale, String manager, String content, String file) {
        this.date = date;
        this.time = time;
        this.sale = sale;
        this.manager = manager;
        this.content = content;
        this.file = file;
    }

    public Contract() {

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

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
