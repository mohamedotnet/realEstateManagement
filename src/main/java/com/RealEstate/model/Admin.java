package com.RealEstate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "admin")

public class Admin extends Person implements Serializable {

    public Admin(int id, String name, String lastName, String username, String sex, Date birthday, String email, String password, String phone, Address address, String idNumber, String picture, String role) {
        super(id, name, lastName, username, sex, birthday, email, password, phone, address, idNumber, picture, role);
    }

    public Admin() {

    }

}
