package com.ironhack.edgeservice.model.entities.invoices;

import com.ironhack.edgeservice.model.enums.InvoiceType;

import java.math.BigDecimal;

/**
 * Invoice
 */
public class Invoice {
    /**
     * Attribute invoiceId of type String
     */
    private String invoiceId;
    /**
     * Attribute userId of type Long
     */
    private Long userId;
    /**
     * Attribute name of type String
     */
    private String name;
    /**
     * Attribute roomId of type Integer
     */
    private Integer roomId;
    /**
     * Attribute invoiceType of type InvoiceType
     */
    private InvoiceType invoiceType;
    /**
     * Attribute priceOwed of type BigDecimal
     */
    private BigDecimal priceOwed;

    /**
     * Empty Invoice's Constructor
     */
    public Invoice() {}

    /**
     * Invoice's Constructor
     * @param userId receives a Long with userId
     * @param name receives a String with name
     * @param roomId receives a Integer with roomId
     * @param invoiceType receives a InvoiceType with invoiceType
     * @param priceOwed receives a BigDecimal with priceOwed
     */
    public Invoice(Long userId, String name, Integer roomId, InvoiceType invoiceType, BigDecimal priceOwed) {
        this.userId = userId;
        this.name = name;
        this.roomId = roomId;
        this.invoiceType = invoiceType;
        this.priceOwed = priceOwed;
    }

    /**
     * Getter of invoiceId
     * @return a String with invoiceId
     */
    public String getInvoiceId() {
        return invoiceId;
    }

    /**
     * Setter of invoiceId
     * @param invoiceId receives a String with invoiceId
     */
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

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
     * Getter of name
     * @return a String with name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name
     * @param name receives a String with name
     */
    public void setName(String name) {
        this.name = name;
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
     * Getter of InvoiceType
     * @return a InvoiceType with invoiceType
     */
    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    /**
     * Setter of InvoiceType
     * @param invoiceType receives a InvoiceType with invoiceType
     */
    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * Getter of priceOwned
     * @return a BigDecimal with priceOwned
     */
    public BigDecimal getPriceOwed() {
        return priceOwed;
    }

    /**
     * Setter of priceOwned
     * @param priceOwed receives a BigDecimal with priceOwned
     */
    public void setPriceOwed(BigDecimal priceOwed) {
        this.priceOwed = priceOwed;
    }
}
