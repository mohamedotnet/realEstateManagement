package com.RealEstate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "customer")
public class Customer extends Person implements Serializable {

    @Column(name = "is_activated", nullable = false)
    private boolean isActivated;
    @Column(name = "is_banned", nullable = false)
    private boolean isBanned;

    public Customer(int id, String name, String lastName, String username, String sex, Date birthday, String email, String password, String phone, Address address, String idNumber, String picture, boolean isActivated, boolean isBanned, String role) {
        super(id, name, lastName, username, sex, birthday, email, password, phone, address, idNumber, picture, role);
        this.isActivated = isActivated;
        this.isBanned = isBanned;
    }

    public Customer() {

    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }
}
