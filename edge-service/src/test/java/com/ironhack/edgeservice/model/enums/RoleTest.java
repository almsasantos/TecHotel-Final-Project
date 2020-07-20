package com.ironhack.edgeservice.model.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    private Role role1;
    private Role role2;
    private Role role3;

    @BeforeEach
    void setUp() {
        role1 = Role.BASIC;
        role2 = Role.PREMIUM;
        role3 = Role.ADMIN;
    }

    @AfterEach
    void tearDown() {
        role1 = null;
        role2 = null;
        role3 = null;
    }

    @Test
    void values() {
        assertEquals(Role.BASIC, role1);
        assertEquals(Role.PREMIUM, role2);
        assertEquals(Role.ADMIN, role3);
    }
}