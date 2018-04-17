package com.RealEstate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "agent")
public class Agent extends Person implements Serializable {

    @Column(name = "nbr_affectation")
    private int nbrAffectation;
    @Column(name = "locality")
    private String locality;

    public Agent(int id, String name, String lastName, String username, String sex, Date birthday, String email, String password, String phone, Address address, String idNumber, String locality, String picture, int nbrAffectation, String role) {
        super(id, name, lastName, username, sex, birthday, email, password, phone, address, idNumber, picture, role);
        this.nbrAffectation = nbrAffectation;
        this.locality = locality;
    }

    public Agent() {

    }

    public int getNbrAffectation() {
        return nbrAffectation;
    }

    public void setNbrAffectation(int nbrAffectation) {
        this.nbrAffectation = nbrAffectation;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
