package com.ironhack.edgeservice.model.entities.users;

import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicTest {
    private Basic basic;
    private Account account;
    private Address address;
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);

    @BeforeEach
    void setUp() {
        basic = new Basic();
        basic = new Basic("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
    }

    @AfterEach
    void tearDown() {
        basic = null;
        account = null;
        address = null;
        birthDate = null;
    }

    @Test
    void getPhoneNumber() {
        basic.setPhoneNumber("987654322");
        assertEquals("987654322", basic.getPhoneNumber());
    }

    @Test
    void getEmail() {
        basic.setEmail("alms@gmail.com");
        assertEquals("alms@gmail.com", basic.getEmail());
    }

    @Test
    void getBirthDate() {
        basic.setBirthDate(LocalDate.of(1998, 9, 19));
        assertEquals(LocalDate.of(1998, 9, 19), basic.getBirthDate());
    }

    @Test
    void getAddress() {
        basic.setAddress(address);
        assertEquals(address, basic.getAddress());
    }

    @Test
    void getBankAccount() {
        basic.setBankAccount(account);
        assertEquals(account, basic.getBankAccount());
    }

    @Test
    void getRegistrationDate() {
        LocalDateTime now = LocalDateTime.now();
        basic.setRegistrationDate(now);
        assertEquals(now, basic.getRegistrationDate());
    }

    @Test
    void getNumberOfStays() {
        basic.setNumberOfStays(1);
        assertEquals(1, basic.getNumberOfStays());
    }

    @Test
    void getRoomId() {
        basic.setRoomId(1);
        assertEquals(1, basic.getRoomId());
    }
}