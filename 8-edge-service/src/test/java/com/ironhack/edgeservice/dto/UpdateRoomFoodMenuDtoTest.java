package com.ironhack.edgeservice.dto;

import com.ironhack.edgeservice.model.enums.FoodMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateRoomFoodMenuDtoTest {
    private UpdateRoomFoodMenuDto updateRoomFoodMenuDto;

    @BeforeEach
    void setUp() {
        updateRoomFoodMenuDto = new UpdateRoomFoodMenuDto(1L, FoodMenu.FRESH_SALMON);
    }

    @AfterEach
    void tearDown() {
        updateRoomFoodMenuDto = null;
    }

    @Test
    void getRoomFoodId() {
        assertEquals(1L, updateRoomFoodMenuDto.getRoomFoodId());
    }

    @Test
    void getFoodMenu() {
        assertEquals(FoodMenu.FRESH_SALMON, updateRoomFoodMenuDto.getFoodMenu());
    }
}