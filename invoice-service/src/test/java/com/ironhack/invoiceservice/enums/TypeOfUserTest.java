package com.ironhack.invoiceservice.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeOfUserTest {
    private TypeOfUser typeOfUser;
    private TypeOfUser typeOfUser1;
    private TypeOfUser typeOfUser2;


    @BeforeEach
    void setUp() {
        typeOfUser = TypeOfUser.ADMIN;
        typeOfUser1 = TypeOfUser.BASIC;
        typeOfUser2 = TypeOfUser.PREMIUM;
    }

    @AfterEach
    void tearDown() {
        typeOfUser = null;
        typeOfUser1 = null;
        typeOfUser2 = null;
    }

    @Test
    void typeOfUser() {
        assertEquals(TypeOfUser.ADMIN, typeOfUser);
        assertEquals(TypeOfUser.BASIC, typeOfUser1);
        assertEquals(TypeOfUser.PREMIUM, typeOfUser2);
    }
}