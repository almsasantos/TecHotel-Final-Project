package com.ironhack.edgeservice.model.entities.rooms;

import com.ironhack.edgeservice.model.enums.RoomType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomTest {
    private RegularRoom regularRoom;

    @BeforeEach
    void setUp() {
        regularRoom = new RegularRoom();

        regularRoom = new RegularRoom(2, true, true, new BigDecimal("89.99"));
    }

    @AfterEach
    void tearDown() {
        regularRoom = null;
    }

    @Test
    void getRoomId() {
        regularRoom.setRoomId(1);

        assertEquals(1, regularRoom.getRoomId());
    }

    @Test
    void getNumberOfBeds() {
        regularRoom.setNumberOfBeds(3);

        assertEquals(3, regularRoom.getNumberOfBeds());
    }

    @Test
    void getHairDryer() {
        regularRoom.setHairDryer(false);

        assertEquals(false, regularRoom.getHairDryer());
    }

    @Test
    void getBathTub() {
        regularRoom.setBathTub(false);

        assertEquals(false, regularRoom.getBathTub());
    }

    @Test
    void getPrice() {
        regularRoom.setPrice(new BigDecimal("59.99"));

        assertEquals(new BigDecimal("59.99"), regularRoom.getPrice());
    }

    @Test
    void getAvailable() {
        regularRoom.setAvailable(true);

        assertEquals(true, regularRoom.getAvailable());
    }

    @Test
    void getRoomType() {
        regularRoom.setRoomType(RoomType.REGULAR_ROOM);

        assertEquals(RoomType.REGULAR_ROOM, regularRoom.getRoomType());
    }
}