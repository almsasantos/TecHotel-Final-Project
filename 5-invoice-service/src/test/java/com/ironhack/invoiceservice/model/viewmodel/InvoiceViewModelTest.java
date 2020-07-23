package com.ironhack.invoiceservice.model.viewmodel;

import com.ironhack.invoiceservice.enums.InvoiceType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceViewModelTest {
    private InvoiceViewModel invoiceViewModel;

    @BeforeEach
    void setUp() {
        invoiceViewModel = new InvoiceViewModel();
    }

    @AfterEach
    void tearDown() {
        invoiceViewModel = null;
    }

    @Test
    void getUserId() {
        invoiceViewModel.setUserId(1L);

        assertEquals(1L, invoiceViewModel.getUserId());
    }

    @Test
    void getRoomId() {
        invoiceViewModel.setRoomId(2);

        assertEquals(2, invoiceViewModel.getRoomId());
    }

    @Test
    void getInvoiceType() {
        invoiceViewModel.setInvoiceType(InvoiceType.POOL_RENT);

        assertEquals(InvoiceType.POOL_RENT, invoiceViewModel.getInvoiceType());
    }

    @Test
    void getPriceOwed() {
        invoiceViewModel.setPriceOwed(new BigDecimal("9.99"));

        assertEquals(new BigDecimal("9.99"), invoiceViewModel.getPriceOwed());
    }

}