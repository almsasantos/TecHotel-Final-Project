package com.ironhack.invoiceservice.model.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTest {
    Money money;
    @BeforeEach
    void setUp() {
        money = new Money();
        money = new Money(new BigDecimal("0"),
                Currency.getInstance("USD"), RoundingMode.HALF_EVEN);
        money = new Money(new BigDecimal("0"), Currency.getInstance("USD"));
        money = new Money(new BigDecimal("0"));
    }

    @Test
    void increaseAmountBigDecimal() {
        BigDecimal param = new BigDecimal("0.00");

        assertEquals(param,money.increaseAmount(param));
    }

    @Test
    void increaseAmountMoney() {
        BigDecimal insideParam = new BigDecimal("0.00");
        Money param = new Money(insideParam);


        assertEquals(insideParam,money.increaseAmount(param));
    }

    @Test
    void decreaseAmountBigDecimal() {
        BigDecimal param = new BigDecimal("0.00");

        assertEquals(param,money.decreaseAmount(param));
    }

    @Test
    void decreaseAmountMoney() {
        BigDecimal insideParam = new BigDecimal("0.00");
        Money param = new Money(insideParam);

        assertEquals(insideParam,money.decreaseAmount(param));
    }

    @Test
    void getCurrency() {
        Currency param = Currency.getInstance("USD");

        assertEquals(param,money.getCurrency());
    }

    @Test
    void getAmount() {
        BigDecimal param = new BigDecimal("0.00");

        assertEquals(param, money.getAmount());
    }

    @Test
    void testToString() {
        String param = "$ 0.00";

        assertEquals(param, money.toString());
    }
}