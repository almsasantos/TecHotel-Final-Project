package com.ironhack.userservice.model.entities;

import com.ironhack.userservice.model.enums.TypeOfUser;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {
    public Admin() {
        this.typeOfUser = TypeOfUser.ADMIN;
    }

    public Admin(String name, String username, String password) {
        super(name, username, password);
        this.typeOfUser = TypeOfUser.ADMIN;
    }
}
