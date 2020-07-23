package com.ironhack.activityservice.model.enums;

import java.math.BigDecimal;

/**
 * Enumerator of Drink Menu
 */
public enum DrinkMenu {
    /**
     * Bottle of water
     */
    BOTTLE_OF_WATER(new BigDecimal("3.99")),
    /**
     * Cappuccino
     */
    CAPPUCCINO(new BigDecimal("4.99")),
    /**
     * Coffee
     */
    COFFEE(new BigDecimal("3.99")),
    /**
     * Tea
     */
    TEA(new BigDecimal("3.99")),
    /**
     * Beer
     */
    BEER(new BigDecimal("5.99")),
    /**
     * Cocktail
     */
    COCKTAIL(new BigDecimal("7.99")),
    /**
     * Glass red wine
     */
    GLASS_RED_WINE(new BigDecimal("6.99")),
    /**
     * Glass green wine
     */
    GLASS_GREEN_WINE(new BigDecimal("6.99"));

    /**
     * Attribute price of type BigDecimal
     */
    private BigDecimal price;

    /**
     * DrinkMenu's Constructor
     * @param price receives a BigDecimal
     */
    DrinkMenu(BigDecimal price) {
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
