package com.ironhack.activityservice.model.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MassageTypeTest {
    private MassageType massageType1;
    private MassageType massageType2;
    private MassageType massageType3;
    private MassageType massageType4;
    private MassageType massageType5;

    @BeforeEach
    void setUp() {
        massageType1 = MassageType.AROMATHERAPY;
        massageType2 = MassageType.COUPLES;
        massageType3 = MassageType.HOT_STONE;
        massageType4 = MassageType.SWEDISH;
        massageType5 = MassageType.THAI;
    }

    @AfterEach
    void tearDown() {
        massageType1 = null;
        massageType2 = null;
        massageType3 = null;
        massageType4 = null;
        massageType5 = null;
    }

    @Test
    void getPrice() {
        assertEquals(MassageType.AROMATHERAPY.getPrice(), massageType1.getPrice());
        assertEquals(MassageType.COUPLES.getPrice(), massageType2.getPrice());
        assertEquals(MassageType.HOT_STONE.getPrice(), massageType3.getPrice());
        assertEquals(MassageType.SWEDISH.getPrice(), massageType4.getPrice());
        assertEquals(MassageType.THAI.getPrice(), massageType5.getPrice());
    }
}