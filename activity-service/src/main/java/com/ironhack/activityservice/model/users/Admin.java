package com.ironhack.activityservice.model.users;

import com.ironhack.activityservice.model.enums.TypeOfUser;

public class Admin extends User {
    public Admin() {
        this.typeOfUser = TypeOfUser.ADMIN;
    }

    public Admin(String name, String username, String password) {
        super(name, username, password);
        this.typeOfUser = TypeOfUser.ADMIN;
    }
}
