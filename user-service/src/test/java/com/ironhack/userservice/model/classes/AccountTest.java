package com.ironhack.userservice.model.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account = new Account(new Money(new BigDecimal("19.99")));
    }

    @Test
    void getBalance() {
        account.setBalance(new Money(new BigDecimal("9.99")));
        assertEquals(new Money(new BigDecimal("9.99")), account.getBalance());
    }
}