package com.ironhack.edgeservice.model.viewModel;

import com.ironhack.edgeservice.model.enums.InvoiceType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class InvoiceViewModel {
    @NotNull(message = "User id cannot be null")
    private Long userId;
    @NotNull(message = "Room id cannot be null")
    private Integer roomId;
    @NotNull(message = "Type of Activity cannot be null")
    private InvoiceType invoiceType;
    @NotNull(message = "Price of Activity cannot be null")
    private BigDecimal priceOwed;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
