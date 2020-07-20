package com.ironhack.invoiceservice.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceTypeTest {
    private InvoiceType invoiceType1;
    private InvoiceType invoiceType2;
    private InvoiceType invoiceType3;
    private InvoiceType invoiceType4;

    @BeforeEach
    void setUp() {
        invoiceType1 = InvoiceType.POOL_RENT;
        invoiceType2 = InvoiceType.ROOM_FOOD_SERVICE;
        invoiceType3 = InvoiceType.MASSAGE_APPOINTMENT;
        invoiceType4 = InvoiceType.END_OF_STAY_AT_THE_HOTEL;
    }

    @AfterEach
    void tearDown() {
        invoiceType1 = null;
        invoiceType2 = null;
        invoiceType3 = null;
        invoiceType4 = null;
    }

    @Test
    void values() {
        assertEquals(InvoiceType.POOL_RENT, invoiceType1);
        assertEquals(InvoiceType.ROOM_FOOD_SERVICE, invoiceType2);
        assertEquals(InvoiceType.MASSAGE_APPOINTMENT, invoiceType3);
        assertEquals(InvoiceType.END_OF_STAY_AT_THE_HOTEL, invoiceType4);
    }
}