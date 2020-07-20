package com.ironhack.activityservice.model.entities;

import com.ironhack.activityservice.model.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long activityId;
    @NotNull
    protected Long userId;
    @NotNull
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
