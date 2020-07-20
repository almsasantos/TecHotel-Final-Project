package com.ironhack.userservice.model.entities;

import com.ironhack.userservice.model.classes.Account;
import com.ironhack.userservice.model.classes.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class PremiumTest {
    private Premium premium;
    private Account account;
    private Address address;
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);

    @BeforeEach
    void setUp() {
        premium = new Premium();
        premium = new Premium("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
    }

    @AfterEach
    void tearDown() {
        premium = null;
        account = null;
        address = null;
        birthDate = null;
    }

    @Test
    void getUserName() {
        premium.setUsername("almsasantos");

        assertEquals("almsasantos", premium.getUsername());
    }
}