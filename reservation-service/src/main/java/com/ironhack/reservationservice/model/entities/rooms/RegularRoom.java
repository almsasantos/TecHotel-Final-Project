package com.ironhack.reservationservice.model.entities.rooms;

import com.ironhack.reservationservice.model.enums.RoomType;

import java.math.BigDecimal;

/**
 * Regular Room's Table
 */
public class RegularRoom extends Room {

    /**
     * Empty Room's Constructor
     */
    public RegularRoom() {
        this.available = true;
        this.hairDryer = true;
        this.bathTub = true;
        this.roomType = RoomType.REGULAR_ROOM;
    }

    /**
     * Room's Constructor
     * @param numberOfBeds receives an Integer with numberOfBeds
     * @param hairDryer receives a Boolean with hairDryer
     * @param bathTub receives a Boolean with bathTub
     * @param price receives a BigDecimal with price
     */
    public RegularRoom(Integer numberOfBeds, Boolean hairDryer, Boolean bathTub, BigDecimal price) {
        super(numberOfBeds, hairDryer, bathTub, price);
        this.available = true;
        this.roomType = RoomType.REGULAR_ROOM;
    }
}