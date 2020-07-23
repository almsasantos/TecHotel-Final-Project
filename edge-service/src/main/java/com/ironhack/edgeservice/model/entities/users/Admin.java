package com.ironhack.edgeservice.model.entities.users;

import com.ironhack.edgeservice.model.enums.TypeOfUser;

/**
 * Admin Users
 */
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
