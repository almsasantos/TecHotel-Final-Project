package com.ironhack.edgeservice.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateTowelNumDtoTest {
    private UpdateTowelNumDto updateTowelNumDto;

    @BeforeEach
    void setUp() {
        updateTowelNumDto = new UpdateTowelNumDto(1L ,1);
    }

    @AfterEach
    void tearDown() {
        updateTowelNumDto = null;
    }

    @Test
    void getPoolRentId() {
        assertEquals(1L, updateTowelNumDto.getPoolRentId());
    }

    @Test
    void getTowelNum() {
        assertEquals(1, updateTowelNumDto.getTowelNum());
    }
}