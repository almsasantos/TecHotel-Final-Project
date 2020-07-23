package com.ironhack.reservationservice.model.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomTypeTest {
    private RoomType roomType;
    private RoomType roomType1;

    @BeforeEach
    void setUp() {
        roomType = RoomType.REGULAR_ROOM;
        roomType1 = RoomType.SUITE_ROOM;
    }

    @AfterEach
    void tearDown() {
        roomType = null;
        roomType1 = null;
    }

    @Test
    void values() {
        assertEquals(RoomType.REGULAR_ROOM, roomType);
        assertEquals(RoomType.SUITE_ROOM, roomType1);
    }
}