package com.ironhack.invoiceservice.model.users;

import com.ironhack.invoiceservice.enums.TypeOfUser;

public class Admin extends User {
    public Admin() {
        this.typeOfUser = TypeOfUser.ADMIN;
    }

    public Admin(String name, String username, String password) {
        super(name, username, password);
        this.typeOfUser = TypeOfUser.ADMIN;
    }
}
