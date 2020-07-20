package com.ironhack.invoiceservice.model.rooms;

import com.ironhack.invoiceservice.enums.RoomType;

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
