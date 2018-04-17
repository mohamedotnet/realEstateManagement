package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "building")
public class Building implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "reference")
    private String reference;
    @Column(name = "nbr_floor")
    private int nbrFloor;
    @Column(name = "locality")
    private String locality;

    public Building(String reference, int nbrFloor, String locality) {
        this.reference = reference;
        this.nbrFloor = nbrFloor;
        this.locality = locality;
    }

    public Building() {

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

    public int getNbrFloor() {
        return nbrFloor;
    }

    public void setNbrFloor(int nbrFloor) {
        this.nbrFloor = nbrFloor;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
