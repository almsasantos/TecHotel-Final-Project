package com.ironhack.edgeservice.model.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoodMenuTest {
    private FoodMenu foodMenu1;
    private FoodMenu foodMenu2;
    private FoodMenu foodMenu3;
    private FoodMenu foodMenu4;
    private FoodMenu foodMenu5;
    private FoodMenu foodMenu6;


    @BeforeEach
    void setUp() {
        foodMenu1 = FoodMenu.FRESH_SALMON;
        foodMenu2 = FoodMenu.BEEF_STROGANOFF;
        foodMenu3 = FoodMenu.HOMEMADE_VEGGIE_SOUP;
        foodMenu4 = FoodMenu.PIZZA_MARGARITA;
        foodMenu5 = FoodMenu.STEAK_GRILLED;
        foodMenu6 = FoodMenu.VEGETARIAN_BURGER;
    }

    @AfterEach
    void tearDown() {
        foodMenu1 = null;
        foodMenu2 = null;
        foodMenu3 = null;
        foodMenu4 = null;
        foodMenu5 = null;
        foodMenu6 = null;
    }

    @Test
    void getPrice() {
        assertEquals(FoodMenu.FRESH_SALMON.getPrice(), foodMenu1.getPrice());
        assertEquals(FoodMenu.BEEF_STROGANOFF.getPrice(), foodMenu2.getPrice());
        assertEquals(FoodMenu.HOMEMADE_VEGGIE_SOUP.getPrice(), foodMenu3.getPrice());
        assertEquals(FoodMenu.PIZZA_MARGARITA.getPrice(), foodMenu4.getPrice());
        assertEquals(FoodMenu.STEAK_GRILLED.getPrice(), foodMenu5.getPrice());
        assertEquals(FoodMenu.VEGETARIAN_BURGER.getPrice(), foodMenu6.getPrice());

    }
}