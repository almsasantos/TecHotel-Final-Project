package com.ironhack.edgeservice.model.entities.rooms;

import com.ironhack.edgeservice.model.enums.RoomType;

import java.math.BigDecimal;

/**
 * Room
 */
public abstract class Room {
    /**
     * Attribute roomId of type String
     */
    protected Integer roomId;

    /**
     * Attribute numberOfBeds of type Integer
     */
    protected Integer numberOfBeds;

    /**
     * Attribute hairDryer of type Boolean
     */
    protected Boolean hairDryer;

    /**
     * Attribute bathTub of type Boolean
     */
    protected Boolean bathTub;

    /**
     * Attribute price of type BigDecimal
     */
    protected BigDecimal price;

    /**
     * Attribute roomType of type RoomType
     */
    protected RoomType roomType;

    /**
     * Attribute available of type Boolean
     */
    protected Boolean available;

    /**
     * Empty Room's Constructor
     */
    public Room() {
        this.available = true;
    }

    /**
     * Room's Constructor
     * @param numberOfBeds receives an Integer with number of Beds
     * @param price receives a BigDecimal with price
     */
    public Room(Integer numberOfBeds, BigDecimal price) {
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.hairDryer = true;
        this.bathTub = true;
        this.available = true;
    }

    /**
     * Room's Constructor
     * @param numberOfBeds receives an Integer with number of Beds
     * @param hairDryer receives a Boolean with hairDryer
     * @param bathTub receives a Boolean with bathTub
     * @param price receives a BigDecimal with price
     */
    public Room(Integer numberOfBeds, Boolean hairDryer, Boolean bathTub, BigDecimal price) {
        this.numberOfBeds = numberOfBeds;
        this.hairDryer = hairDryer;
        this.bathTub = bathTub;
        this.price = price;
        this.available = true;
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
     * Getter of numberOfBeds
     * @return an Integer with numberOfBeds
     */
    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Setter of numberOfBeds
     * @param numberOfBeds receives an Integer with numberOfBeds
     */
    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    /**
     * Getter of hairDryer
     * @return a Boolean with hairDryer
     */
    public Boolean getHairDryer() {
        return hairDryer;
    }

    /**
     * Setter of hairDryer
     * @param hairDryer receives a Boolean with hairDryer
     */
    public void setHairDryer(Boolean hairDryer) {
        this.hairDryer = hairDryer;
    }

    /**
     * Getter of bathTub
     * @return a Boolean with bathTub
     */
    public Boolean getBathTub() {
        return bathTub;
    }

    /**
     * Setter of bathTub
     * @param bathTub receives a Boolean with bathTub
     */
    public void setBathTub(Boolean bathTub) {
        this.bathTub = bathTub;
    }

    /**
     * Getter of price
     * @return a BigDecimal with price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Setter of price
     * @param price receives a BigDecimal with price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Getter of available
     * @return a Boolean with available
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * Setter of available
     * @param available receives a Boolean with available
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * Getter of roomType
     * @return a RoomType with roomType
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * Setter of roomType
     * @param roomType receives a RoomType with roomType
     */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
