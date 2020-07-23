package com.ironhack.edgeservice.dto;

import com.ironhack.edgeservice.model.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto("admin", "admin", Role.ADMIN);
    }

    @AfterEach
    void tearDown() {
        userDto = null;
    }

    @Test
    void getUsername() {
        userDto.setUsername("alms");

        assertEquals("alms", userDto.getUsername());
    }

    @Test
    void getPassword() {
        userDto.setPassword("pass");

        assertEquals("pass", userDto.getPassword());
    }

    @Test
    void getRol() {
        userDto.setRol(Role.BASIC);

        assertEquals(Role.BASIC, userDto.getRol());
    }
}