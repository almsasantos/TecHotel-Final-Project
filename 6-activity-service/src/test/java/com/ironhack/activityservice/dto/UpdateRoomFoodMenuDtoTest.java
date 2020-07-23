package com.ironhack.activityservice.dto;

import com.ironhack.activityservice.model.enums.FoodMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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