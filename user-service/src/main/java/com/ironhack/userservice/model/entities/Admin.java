package com.ironhack.userservice.model.entities;

import com.ironhack.userservice.model.enums.TypeOfUser;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Table with Admin Users
 */
@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {
    /**
     * Empty Admin's Constructor
     */
    public Admin() {
        this.typeOfUser = TypeOfUser.ADMIN;
    }

    /**
     * Admin's Constructor
     * @param name receives a String with name
     * @param username receives a String with username
     * @param password receives a String with password
     */
    public Admin(String name, String username, String password) {
        super(name, username, password);
        this.typeOfUser = TypeOfUser.ADMIN;
    }
}
