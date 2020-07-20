package com.ironhack.reservationservice.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateRoomReservationDtoTest {
    private UpdateRoomReservationDto updateRoomReservationDto;

    @BeforeEach
    void setUp() {
        updateRoomReservationDto = new UpdateRoomReservationDto();
    }

    @AfterEach
    void tearDown() {
        updateRoomReservationDto = null;
    }

    @Test
    void getUserId() {
        updateRoomReservationDto.setUserId(1L);

        assertEquals(1L, updateRoomReservationDto.getUserId());
    }

    @Test
    void getUserName() {
        updateRoomReservationDto.setUserName("Ana Santos");

        assertEquals("Ana Santos", updateRoomReservationDto.getUserName());
    }

    @Test
    void getRoomId() {
        updateRoomReservationDto.setRoomId(1);

        assertEquals(1, updateRoomReservationDto.getRoomId());
    }
}