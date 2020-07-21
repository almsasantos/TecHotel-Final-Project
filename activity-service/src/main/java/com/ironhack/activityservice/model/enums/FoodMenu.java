package com.ironhack.activityservice.model.enums;

import java.math.BigDecimal;

/**
 * Enumerator of Food Menu
 */
public enum FoodMenu {
    /**
     * Steak Grilled
     */
    STEAK_GRILLED(new BigDecimal("21.99")),
    /**
     * Fresh Salmon
     */
    FRESH_SALMON(new BigDecimal("18.99")),
    /**
     * Beef Stroganoff
     */
    BEEF_STROGANOFF(new BigDecimal("18.99")),
    /**
     * Pizza Margarita
     */
    PIZZA_MARGARITA(new BigDecimal("16.99")),
    /**
     * Homemade veggie soup
     */
    HOMEMADE_VEGGIE_SOUP(new BigDecimal("12.99")),
    /**
     * Vegetarian Burger
     */
    VEGETARIAN_BURGER(new BigDecimal("19.99"));

    /**
     * Attribute price of type BigDecimal
     */
    private BigDecimal price;

    /**
     * FoodMenu's Constructor
     * @param price receives a BigDecimal
     */
    FoodMenu (BigDecimal price) {
        this.price = price;
    }

    /**
     * Getter of price
     * @return a BigDecimal with price
     */
    public BigDecimal getPrice() {
        return price;
    }
}
