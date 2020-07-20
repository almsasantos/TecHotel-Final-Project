package com.ironhack.reservationservice.model.entities.rooms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SuiteRoomTest {
    private SuiteRoom suiteRoom;

    @BeforeEach
    void setUp() {
        suiteRoom = new SuiteRoom();
        suiteRoom = new SuiteRoom(2, new BigDecimal("89.99"));
    }

    @AfterEach
    void tearDown() {
        suiteRoom = null;
    }

    @Test
    void getRoomId() {
        suiteRoom.setRoomId(1);

        assertEquals(1, suiteRoom.getRoomId());
    }
}