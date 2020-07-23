package com.ironhack.edgeservice.model.viewModel;

import com.ironhack.edgeservice.model.enums.DrinkMenu;
import com.ironhack.edgeservice.model.enums.FoodMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoomFoodViewModelTest {
    private RoomFoodViewModel roomFoodViewModel;

    @BeforeEach
    void setUp() {
        roomFoodViewModel = new RoomFoodViewModel();
    }

    @AfterEach
    void tearDown() {
        roomFoodViewModel = null;
    }

    @Test
    void getUserId() {
        roomFoodViewModel.setUserId(1L);

        assertEquals(1L ,roomFoodViewModel.getUserId());
    }

    @Test
    void getRoomId() {
        roomFoodViewModel.setRoomId(1);

        assertEquals(1, roomFoodViewModel.getRoomId());
    }

    @Test
    void getFoodMenu() {
        roomFoodViewModel.setFoodMenu(FoodMenu.FRESH_SALMON);

        assertEquals(FoodMenu.FRESH_SALMON, roomFoodViewModel.getFoodMenu());
    }

    @Test
    void getDrinkMenu() {
        roomFoodViewModel.setDrinkMenu(DrinkMenu.CAPPUCCINO);

        assertEquals(DrinkMenu.CAPPUCCINO, roomFoodViewModel.getDrinkMenu());
    }
}