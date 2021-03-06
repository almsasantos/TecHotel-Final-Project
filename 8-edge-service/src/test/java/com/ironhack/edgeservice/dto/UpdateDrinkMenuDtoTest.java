package com.ironhack.edgeservice.dto;

import com.ironhack.edgeservice.model.enums.DrinkMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateDrinkMenuDtoTest {
    private UpdateDrinkMenuDto updateDrinkMenuDto;

    @BeforeEach
    void setUp() {
        updateDrinkMenuDto = new UpdateDrinkMenuDto(1L, DrinkMenu.CAPPUCCINO);
    }

    @AfterEach
    void tearDown() {
        updateDrinkMenuDto = null;
    }

    @Test
    void getRoomFoodId() {
        assertEquals(1L, updateDrinkMenuDto.getRoomFoodId());
    }

    @Test
    void getDrinkMenu() {
        assertEquals(DrinkMenu.CAPPUCCINO, updateDrinkMenuDto.getDrinkMenu());
    }
}