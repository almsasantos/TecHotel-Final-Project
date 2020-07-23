package com.ironhack.invoiceservice.model.activities;

import com.ironhack.invoiceservice.model.classes.Money;

/**
 * Activity
 */
public abstract class Activity {
    /**
     * Attribute activityId of type Long
     */
    protected Long activityId;
    /**
     * Attribute userId of type Long
     */
    protected Long userId;
    /**
     * Attribute roomId of type Integer
     */
    protected Integer roomId;
    /**
     * Attribute totalPrice of type Money
     */
    protected Money totalPrice;

    /**
     * Empty Activity's Constructor
     */
    public Activity() {}

    /**
     * Activity's Constructor
     * @param userId receives a Long with userId
     * @param roomId receives an Integer with roomId
     */
    public Activity(Long userId, Integer roomId) {
        this.userId = userId;
        this.roomId = roomId;
        this.totalPrice = null;
    }

    /**
     * Getter of activityId
     * @return a Long with activityId
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * Setter of activityId
     * @param activityId receives a Long with activityId
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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
     * Getter of roomId
     * @return an Integer with roomId
     */
    public Integer getRoomId() {
        return roomId;
    }

    /**
     * Setter of roomId
     * @param roomId receives an Integer with roomId
     */
    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    /**
     * Getter of totalPrice
     * @return a Money with totalPrice
     */
    public Money getTotalPrice() {
        return totalPrice;
    }

    /**
     * Setter of totalPrice
     * @param totalPrice receives a Money with totalPrice
     */
    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }
}
