package com.ironhack.invoiceservice.model.activities;

import com.ironhack.invoiceservice.model.classes.Money;

public abstract class Activity {
    protected Long activityId;
    protected Long userId;
    protected Integer roomId;
    protected Money totalPrice;

    public Activity() {}

    public Activity(Long userId, Integer roomId) {
        this.userId = userId;
        this.roomId = roomId;
        this.totalPrice = null;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

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

    public Money getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }
}
