package com.ironhack.invoiceservice.model.activities;

import com.ironhack.invoiceservice.enums.DrinkMenu;
import com.ironhack.invoiceservice.enums.FoodMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoomFoodTest {
    private RoomFood roomFood;

    @BeforeEach
    void setUp() {
        roomFood = new RoomFood();
        roomFood = new RoomFood(1L, 1, FoodMenu.BEEF_STROGANOFF, DrinkMenu.BEER);
    }

    @AfterEach
    void tearDown() {
        roomFood = null;
    }

    @Test
    void getFoodMenu() {
        roomFood.setFoodMenu(FoodMenu.FRESH_SALMON);

        assertEquals(FoodMenu.FRESH_SALMON, roomFood.getFoodMenu());
    }

    @Test
    void getDrinkMenu() {
        roomFood.setDrinkMenu(DrinkMenu.CAPPUCCINO);

        assertEquals(DrinkMenu.CAPPUCCINO, roomFood.getDrinkMenu());
    }

    @Test
    void getDelivered() {
        roomFood.setDelivered(true);

        assertTrue(roomFood.getDelivered());
    }
}