package com.ironhack.bookservice.model;

import com.ironhack.bookservice.enums.RoomType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Integer roomId;
    @NotNull
    protected Integer numberOfBeds;
    protected Boolean hairDryer;
    protected Boolean bathTub;
    @NotNull
    protected BigDecimal price;

    @Enumerated(EnumType.STRING)
    protected RoomType roomType;
    protected Boolean available;

    public Room() {
        this.available = true;
    }

    public Room(Integer numberOfBeds, BigDecimal price) {
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.hairDryer = true;
        this.bathTub = true;
        this.available = true;
    }

    public Room(Integer numberOfBeds, Boolean hairDryer, Boolean bathTub, BigDecimal price) {
        this.numberOfBeds = numberOfBeds;
        this.hairDryer = hairDryer;
        this.bathTub = bathTub;
        this.price = price;
        this.available = true;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public Boolean getHairDryer() {
        return hairDryer;
    }

    public void setHairDryer(Boolean hairDryer) {
        this.hairDryer = hairDryer;
    }

    public Boolean getBathTub() {
        return bathTub;
    }

    public void setBathTub(Boolean bathTub) {
        this.bathTub = bathTub;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
