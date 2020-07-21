package com.ironhack.activityservice.model.viewModel;

import com.ironhack.activityservice.model.enums.InvoiceType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Invoice View Model
 */
public class InvoiceViewModel {
    /**
     * Attribute userId of type Long
     */
    @NotNull(message = "User id cannot be null")
    private Long userId;
    /**
     * Attribute roomId of type Integer
     */
    @NotNull(message = "Room id cannot be null")
    private Integer roomId;
    /**
     * Attribute invoiceType of type InvoiceType
     */
    @NotNull(message = "Type of Activity cannot be null")
    private InvoiceType invoiceType;
    /**
     * Attribute priceOwed of type BigDecimal
     */
    @NotNull(message = "Price of Activity cannot be null")
    private BigDecimal priceOwed;

    /**
     * Getter of userId
     * @return a Long with userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter of userId
     * @param userId receives a Long with userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter of roomId
     * @return a Integer with roomId
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * Setter of roomId
     * @param roomId receives a Integer with roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * Getter of invoiceType
     * @return a InvoiceType with invoiceType
     */
    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    /**
     * Setter of invoiceType
     * @param invoiceType receives a InvoiceType with invoiceType
     */
    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * Getter of priceOwed
     * @return a BigDecimal with priceOwed
     */
    public BigDecimal getPriceOwed() {
        return priceOwed;
    }

    /**
     * Setter of priceOwed
     * @param priceOwed receives a BigDecimal with priceOwed
     */
    public void setPriceOwed(BigDecimal priceOwed) {
        this.priceOwed = priceOwed;
    }
}
