package com.ironhack.reservationservice.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateNumberOfNightsDtoTest {
    private UpdateNumberOfNightsDto updateNumberOfNightsDto;

    @BeforeEach
    void setUp() {
        updateNumberOfNightsDto = new UpdateNumberOfNightsDto();
    }

    @AfterEach
    void tearDown() {
        updateNumberOfNightsDto = null;
    }

    @Test
    void getUserId() {
        updateNumberOfNightsDto.setUserId(1L);

        assertEquals(1L, updateNumberOfNightsDto.getUserId());
    }

    @Test
    void getNumberOfNights() {
        updateNumberOfNightsDto.setNumberOfNights(2);

        assertEquals(2, updateNumberOfNightsDto.getNumberOfNights());
    }
}