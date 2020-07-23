package com.ironhack.edgeservice.dto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateFloatiesNumDtoTest {
    private UpdateFloatiesNumDto updateFloatiesNumDto;

    @BeforeEach
    void setUp() {
        updateFloatiesNumDto = new UpdateFloatiesNumDto(1L, 2);
    }

    @AfterEach
    void tearDown() {
        updateFloatiesNumDto = null;
    }

    @Test
    void getPoolRentId() {
        assertEquals(1L, updateFloatiesNumDto.getPoolRentId());
    }

    @Test
    void getFloatiesNum() {
        assertEquals(2, updateFloatiesNumDto.getFloatiesNum());
    }
}