package com.ironhack.activityservice.model.rooms;

import com.ironhack.activityservice.model.enums.RoomType;

import java.math.BigDecimal;

/**
 * Suite's Room
 */
public class SuiteRoom extends Room {
    /**
     * Empty Suite's Constructor
     */
    public SuiteRoom() {
        this.hairDryer = true;
        this.bathTub = true;
        this.available = true;
        this.roomType = RoomType.SUITE_ROOM;
    }

    /**
     * Suite's Constructor
     * @param numberOfBeds receives an Integer with numberOfBeds
     * @param price receives a BigDecimal with price
     */
    public SuiteRoom(Integer numberOfBeds, BigDecimal price) {
        super(numberOfBeds, price);
        this.hairDryer = true;
        this.bathTub = true;
        this.available = true;
        this.roomType = RoomType.SUITE_ROOM;
    }
}
