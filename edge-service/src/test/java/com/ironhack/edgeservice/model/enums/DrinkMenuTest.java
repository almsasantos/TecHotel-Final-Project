package com.ironhack.edgeservice.model.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkMenuTest {
    private DrinkMenu drinkMenu;
    private DrinkMenu drinkMenu1;
    private DrinkMenu drinkMenu2;
    private DrinkMenu drinkMenu3;
    private DrinkMenu drinkMenu4;
    private DrinkMenu drinkMenu5;
    private DrinkMenu drinkMenu6;
    private DrinkMenu drinkMenu7;

    @BeforeEach
    void setUp() {
        drinkMenu = DrinkMenu.CAPPUCCINO;
        drinkMenu1 = DrinkMenu.TEA;
        drinkMenu2 = DrinkMenu.BEER;
        drinkMenu3 = DrinkMenu.BOTTLE_OF_WATER;
        drinkMenu4 = DrinkMenu.COCKTAIL;
        drinkMenu5 = DrinkMenu.COFFEE;
        drinkMenu6 = DrinkMenu.GLASS_GREEN_WINE;
        drinkMenu7 = DrinkMenu.GLASS_RED_WINE;

    }

    @AfterEach
    void tearDown() {
        drinkMenu = null;
        drinkMenu1 = null;
        drinkMenu2 = null;
        drinkMenu3 = null;
        drinkMenu4 = null;
        drinkMenu5 = null;
        drinkMenu6 = null;
        drinkMenu7 = null;
    }

    @Test
    void getPrice() {
        assertEquals(DrinkMenu.CAPPUCCINO.getPrice(), drinkMenu.getPrice());
        assertEquals(DrinkMenu.TEA.getPrice(), drinkMenu1.getPrice());
        assertEquals(DrinkMenu.BEER.getPrice(), drinkMenu2.getPrice());
        assertEquals(DrinkMenu.BOTTLE_OF_WATER.getPrice(), drinkMenu3.getPrice());
        assertEquals(DrinkMenu.COCKTAIL.getPrice(), drinkMenu4.getPrice());
        assertEquals(DrinkMenu.COFFEE.getPrice(), drinkMenu5.getPrice());
        assertEquals(DrinkMenu.GLASS_GREEN_WINE.getPrice(), drinkMenu6.getPrice());
        assertEquals(DrinkMenu.GLASS_RED_WINE.getPrice(), drinkMenu7.getPrice());
    }
}