package com.ironhack.invoiceservice.model.entities;

import com.ironhack.invoiceservice.enums.InvoiceType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceTest {
    private Invoice invoice;

    @BeforeEach
    void setUp() {
        invoice = new Invoice();
        invoice = new Invoice(1L, "Ana Martins", 2, InvoiceType.ROOM_FOOD_SERVICE, new BigDecimal("9.99"));
    }

    @AfterEach
    void tearDown() {
        invoice = null;
    }

    @Test
    void getInvoiceId() {
        invoice.setInvoiceId("1");

        assertEquals("1", invoice.getInvoiceId());
    }

    @Test
    void getUserId() {
        invoice.setUserId(2L);

        assertEquals(2L, invoice.getUserId());
    }

    @Test
    void getName() {
        invoice.setName("Ana Santos");

        assertEquals("Ana Santos", invoice.getName());
    }

    @Test
    void getRoomId() {
        invoice.setRoomId(1);

        assertEquals(1, invoice.getRoomId());
    }

    @Test
    void getInvoiceType() {
        invoice.setInvoiceType(InvoiceType.POOL_RENT);

        assertEquals(InvoiceType.POOL_RENT, invoice.getInvoiceType());
    }

    @Test
    void getPriceOwed() {
        invoice.setPriceOwed(new BigDecimal("19.99"));

        assertEquals(new BigDecimal("19.99"), invoice.getPriceOwed());
    }
}