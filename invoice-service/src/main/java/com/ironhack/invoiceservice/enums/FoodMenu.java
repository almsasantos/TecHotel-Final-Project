package com.ironhack.invoiceservice.enums;

import java.math.BigDecimal;

public enum FoodMenu {
    STEAK_GRILLED(new BigDecimal("21.99")),
    FRESH_SALMON(new BigDecimal("18.99")),
    BEEF_STROGANOFF(new BigDecimal("18.99")),
    PIZZA_MARGARITA(new BigDecimal("16.99")),
    HOMEMADE_VEGGIE_SOUP(new BigDecimal("12.99")),
    VEGETARIAN_BURGER(new BigDecimal("19.99"));

    private BigDecimal price;

    FoodMenu (BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
