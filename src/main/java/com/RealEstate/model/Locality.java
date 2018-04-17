package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "locality")
public class Locality implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "nbr_building")
    private int nbrBuilding;
    @Column(name = "nbr_parking")
    private int nbrParking;

    public Locality(String name, Address address, int nbrBuilding, int nbrParking) {
        this.name = name;
        this.address = address.toString();
        this.nbrBuilding = nbrBuilding;
        this.nbrParking = nbrParking;
    }

    public Locality() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNbrBuilding() {
        return nbrBuilding;
    }

    public void setNbrBuilding(int nbrBuilding) {
        this.nbrBuilding = nbrBuilding;
    }

    public int getNbrParking() {
        return nbrParking;
    }

    public void setNbrParking(int nbrParking) {
        this.nbrParking = nbrParking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
