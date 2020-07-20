package com.ironhack.edgeservice.model.entities.rooms;

import com.ironhack.edgeservice.model.enums.RoomType;

import java.math.BigDecimal;

public class SuiteRoom extends Room {
    public SuiteRoom() {
        this.hairDryer = true;
        this.bathTub = true;
        this.available = true;
        this.roomType = RoomType.SUITE_ROOM;
    }

    public SuiteRoom(Integer numberOfBeds, BigDecimal price) {
        super(numberOfBeds, price);
        this.hairDryer = true;
        this.bathTub = true;
        this.available = true;
        this.roomType = RoomType.SUITE_ROOM;
    }
}
