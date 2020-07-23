package com.ironhack.invoiceservice.model.activities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PoolRentTest {
    private PoolRent poolRent;

    @BeforeEach
    void setUp() {
        poolRent = new PoolRent();
        poolRent = new PoolRent(1L, 2, 1, 1, LocalDateTime.now());
    }

    @AfterEach
    void tearDown() {
        poolRent = null;
    }

    @Test
    void getFloatiesNum() {
        poolRent.setFloatiesNum(2);

        assertEquals(2, poolRent.getFloatiesNum());
    }

    @Test
    void getTowelNum() {
        poolRent.setTowelNum(3);

        assertEquals(3, poolRent.getTowelNum());
    }

    @Test
    void getBeginOfActivity() {
        LocalDateTime now = LocalDateTime.now();
        poolRent.setBeginOfActivity(now);

        assertEquals(now, poolRent.getBeginOfActivity());
    }

    @Test
    void getDuration() {
        poolRent.setDuration(LocalTime.of(1, 00));

        assertEquals(LocalTime.of(1, 00), poolRent.getDuration());
    }

    @Test
    void getEndOfActivity() {
        LocalDateTime now = LocalDateTime.now();
        poolRent.setEndOfActivity(now);

        assertEquals(now, poolRent.getEndOfActivity());
    }
}