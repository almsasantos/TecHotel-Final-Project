package com.ironhack.edgeservice.model.enums;

import java.math.BigDecimal;

public enum DrinkMenu {
    BOTTLE_OF_WATER(new BigDecimal("3.99")),
    CAPPUCCINO(new BigDecimal("4.99")),
    COFFEE(new BigDecimal("3.99")),
    TEA(new BigDecimal("3.99")),
    BEER(new BigDecimal("5.99")),
    COCKTAIL(new BigDecimal("7.99")),
    GLASS_RED_WINE(new BigDecimal("6.99")),
    GLASS_GREEN_WINE(new BigDecimal("6.99"));

    private BigDecimal price;

    DrinkMenu(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
