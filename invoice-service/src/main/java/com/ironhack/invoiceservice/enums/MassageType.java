package com.ironhack.invoiceservice.enums;

import java.math.BigDecimal;

/**
 * Enumerator of Massage Type
 */
public enum MassageType {
    /**
     * Swedish type of massage
     */
    SWEDISH(new BigDecimal("39.99")),
    /**
     * Hot Stone type of massage
     */
    HOT_STONE(new BigDecimal("59.99")),
    /**
     * Aromatherapy type of massage
     */
    AROMATHERAPY(new BigDecimal("69.99")),
    /**
     * Thai type of massage
     */
    THAI(new BigDecimal("59.99")),
    /**
     * Couples type of massage
     */
    COUPLES(new BigDecimal("89.99"));

    /**
     * Attribute price of type BigDecimal
     */
    private BigDecimal price;

    /**
     * MassageType's Constructor
     * @param price receives a BigDecimal with price
     */
    MassageType(BigDecimal price) {
        this.price = price;
    }

    /**
     * Getter of Price
     * @return a BigDecimal with price
     */
    public BigDecimal getPrice() {
        return price;
    }
}
