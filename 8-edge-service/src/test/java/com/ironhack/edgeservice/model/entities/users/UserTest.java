package com.ironhack.edgeservice.model.entities.users;

import com.ironhack.edgeservice.model.enums.TypeOfUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin();
        admin = new Admin("Ana Santos", "alms", "pass");
    }

    @AfterEach
    void tearDown() {
        admin = null;
    }

    @Test
    void getId() {
        admin.setId(1L);
        assertEquals(1L, admin.getId());
    }

    @Test
    void getName() {
        admin.setName("Ana Martins");
        assertEquals("Ana Martins", admin.getName());
    }

    @Test
    void getUsername() {
        admin.setUsername("almsasantos");
        assertEquals("almsasantos", admin.getUsername());
    }

    @Test
    void getPassword() {
        admin.setPassword("password");
        assertEquals("password", admin.getPassword());
    }

    @Test
    void getTypeOfUser() {
        admin.setTypeOfUser(TypeOfUser.ADMIN);
        assertEquals(TypeOfUser.ADMIN, admin.getTypeOfUser());
    }
}