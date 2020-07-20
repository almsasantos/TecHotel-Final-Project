package com.ironhack.reservationservice.model.invoices;

import com.ironhack.reservationservice.model.enums.InvoiceType;

import java.math.BigDecimal;

public class Invoice {
    private String invoiceId;
    private Long userId;
    private String name;
    private Integer roomId;
    private InvoiceType invoiceType;
    private BigDecimal priceOwed;

    public Invoice() {}

    public Invoice(Long userId, String name, Integer roomId, InvoiceType invoiceType, BigDecimal priceOwed) {
        this.userId = userId;
        this.name = name;
        this.roomId = roomId;
        this.invoiceType = invoiceType;
        this.priceOwed = priceOwed;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public BigDecimal getPriceOwed() {
        return priceOwed;
    }

    public void setPriceOwed(BigDecimal priceOwed) {
        this.priceOwed = priceOwed;
    }
}
