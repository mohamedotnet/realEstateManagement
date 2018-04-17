package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "apartment")
public class Apartment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "reference")
    private String reference;
    @Column(name = "type")
    private String type;
    @Column(name = "floor")
    private int floor;
    @Column(name = "price")
    private int price;
    @Column(name = "id_building")
    private int building;
    @Column(name = "surface")
    private int surface;
    @Column(name = "nbr_room")
    private int nbrRoom;
    @Column(name = "nbr_balcony")
    private int nbrBalcony;
    @Column(name = "status")
    private boolean status;
    @Column(name = "picture1")
    private String picture1;
    @Column(name = "picture2")
    private String picture2;
    @Column(name = "picture3")
    private String picture3;
    @Column(name = "picture4")
    private String picture4;
    @Column(name = "picture5")
    private String picture5;

    public Apartment(String reference, String type, int floor, int price, int building, int surface, int nbrRoom, int nbrBalcony, boolean status, String picture1, String picture2, String picture3, String picture4, String picture5) {
        this.reference = reference;
        this.type = type;
        this.floor = floor;
        this.price = price;
        this.building = building;
        this.surface = surface;
        this.nbrRoom = nbrRoom;
        this.nbrBalcony = nbrBalcony;
        this.status = status;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.picture4 = picture4;
        this.picture5 = picture5;
    }

    public Apartment() {

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getNbrRoom() {
        return nbrRoom;
    }

    public void setNbrRoom(int nbrRoom) {
        this.nbrRoom = nbrRoom;
    }

    public int getNbrBalcony() {
        return nbrBalcony;
    }

    public void setNbrBalcony(int nbrBalcony) {
        this.nbrBalcony = nbrBalcony;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getPicture4() {
        return picture4;
    }

    public void setPicture4(String picture4) {
        this.picture4 = picture4;
    }

    public String getPicture5() {
        return picture5;
    }

    public void setPicture5(String picture5) {
        this.picture5 = picture5;
    }
}
