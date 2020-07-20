package com.ironhack.activityservice.model.enums;

import java.math.BigDecimal;

public enum MassageType {
    SWEDISH(new BigDecimal("39.99")),
    HOT_STONE(new BigDecimal("59.99")),
    AROMATHERAPY(new BigDecimal("69.99")),
    THAI(new BigDecimal("59.99")),
    COUPLES(new BigDecimal("89.99"));

    private BigDecimal price;

    MassageType(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
