package com.ironhack.activityservice.model.entities;

import com.ironhack.activityservice.model.classes.Money;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Activity's Table
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Activity {
    /**
     * Attribute activityId of type Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long activityId;
    /**
     * Attribute userId of type Long
     */
    @NotNull
    protected Long userId;
    /**
     * Attribute roomId of type Integer
     */
    @NotNull
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
