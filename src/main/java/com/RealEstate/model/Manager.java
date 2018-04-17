package com.RealEstate.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "manager")
public class Manager extends Person implements Serializable {

    public Manager(int id, String name, String lastName, String username, String sex, Date birthday, String email, String password, String phone, Address address, String idNumber, String picture, String role) {
        super(id, name, lastName, username, sex, birthday, email, password, phone, address, idNumber, picture, role);
    }

    public Manager() {

    }
}
