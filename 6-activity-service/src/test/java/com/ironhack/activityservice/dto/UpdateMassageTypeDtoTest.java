package com.ironhack.activityservice.dto;

import com.ironhack.activityservice.model.enums.MassageType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateMassageTypeDtoTest {
    private UpdateMassageTypeDto updateMassageTypeDto;

    @BeforeEach
    void setUp() {
        updateMassageTypeDto = new UpdateMassageTypeDto(1L, MassageType.COUPLES);
    }

    @AfterEach
    void tearDown() {
        updateMassageTypeDto = null;
    }

    @Test
    void getMassageId() {
        assertEquals(1L, updateMassageTypeDto.getMassageId());
    }

    @Test
    void getMassageType() {
        assertEquals(MassageType.COUPLES, updateMassageTypeDto.getMassageType());
    }
}