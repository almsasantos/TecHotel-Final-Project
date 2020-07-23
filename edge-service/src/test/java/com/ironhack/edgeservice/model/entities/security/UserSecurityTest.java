package com.ironhack.edgeservice.model.entities.security;

import com.ironhack.edgeservice.model.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSecurityTest {
    private UserSecurity userSecurity;

    @BeforeEach
    void setUp() {
        userSecurity = new UserSecurity();
        userSecurity = new UserSecurity("alms", "pass", Role.BASIC);
    }

    @AfterEach
    void tearDown() {
        userSecurity = null;
    }

    @Test
    void getId() {
        userSecurity.setId(1L);

        assertEquals(1L, userSecurity.getId());
    }

    @Test
    void getUsername() {
        userSecurity.setUsername("admin");

        assertEquals("admin", userSecurity.getUsername());
    }

    @Test
    void getPassword() {
        userSecurity.setPassword("admin");

        assertEquals("admin", userSecurity.getPassword());
    }

    @Test
    void getRol() {
        userSecurity.setRol(Role.ADMIN);

        assertEquals(Role.ADMIN, userSecurity.getRol());
    }

    @Test
    void testEquals() {
        UserSecurity userSecurity1 = new UserSecurity("alms", "pass", Role.BASIC);

        assertEquals(userSecurity, userSecurity1);
    }

    @Test
    void testHashCode() {
        UserSecurity userSecurity1 = new UserSecurity("alms", "pass", Role.BASIC);

        assertEquals(userSecurity.hashCode(), userSecurity1.hashCode());
    }
}