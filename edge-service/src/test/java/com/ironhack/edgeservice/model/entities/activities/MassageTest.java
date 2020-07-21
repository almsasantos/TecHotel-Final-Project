package com.ironhack.edgeservice.model.entities.activities;

import com.ironhack.edgeservice.model.enums.MassageType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MassageTest {
    private Massage massage;

    @BeforeEach
    void setUp() {
        massage = new Massage();
        massage = new Massage(1L, 2, MassageType.AROMATHERAPY);
    }

    @AfterEach
    void tearDown() {
        massage = null;
    }

    @Test
    void getMassageType() {
        massage.setMassageType(MassageType.COUPLES);

        assertEquals(MassageType.COUPLES, massage.getMassageType());
    }

    @Test
    void getBeginOfActivity() {
        LocalDateTime now = LocalDateTime.now();
        massage.setBeginOfActivity(now);

        assertEquals(now, massage.getBeginOfActivity());
    }

    @Test
    void getDuration() {
        massage.setDuration(LocalTime.of(1, 00));

        assertEquals(LocalTime.of(1, 00), massage.getDuration());
    }

    @Test
    void getEndOfActivity() {
        LocalDateTime now = LocalDateTime.now();
        massage.setEndOfActivity(now);

        assertEquals(now, massage.getEndOfActivity());
    }
}