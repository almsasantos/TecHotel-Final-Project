package com.ironhack.activityservice.model.users;

import com.ironhack.activityservice.model.classes.Account;
import com.ironhack.activityservice.model.classes.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void getPhoneNumber() {
        premium.setPhoneNumber("987654322");

        assertEquals("987654322", premium.getPhoneNumber());
    }

    @Test
    void getEmail() {
        premium.setEmail("alms@gmail.com");

        assertEquals("alms@gmail.com", premium.getEmail());
    }

    @Test
    void getBirthDate() {
        premium.setBirthDate(LocalDate.of(1998, 9, 19));

        assertEquals(LocalDate.of(1998, 9, 19), premium.getBirthDate());
    }

    @Test
    void getAddress() {
        premium.setAddress(address);

        assertEquals(address, premium.getAddress());
    }
    @Test
    void getRegistrationDate() {
        LocalDateTime now = LocalDateTime.now();
        premium.getRegistrationDate();
    }

    @Test
    void getBankAccount() {
        premium.setBankAccount(account);

        assertEquals(account, premium.getBankAccount());
    }

    @Test
    void getNumberOfStays() {
        premium.setNumberOfStays(1);

        assertEquals(1, premium.getNumberOfStays());
    }

    @Test
    void getRoomId() {
        premium.setRoomId(1);

        assertEquals(1, premium.getRoomId());
    }
}