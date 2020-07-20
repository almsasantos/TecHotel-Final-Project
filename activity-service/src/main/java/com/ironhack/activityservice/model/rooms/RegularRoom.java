package com.ironhack.activityservice.model.rooms;

import com.ironhack.activityservice.model.enums.RoomType;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class RegularRoom extends Room {
    public RegularRoom() {
        this.available = true;
        this.hairDryer = true;
        this.bathTub = true;
        this.roomType = RoomType.REGULAR_ROOM;
    }

    public RegularRoom(Integer numberOfBeds, Boolean hairDryer, Boolean bathTub, BigDecimal price) {
        super(numberOfBeds, hairDryer, bathTub, price);
        this.available = true;
        this.roomType = RoomType.REGULAR_ROOM;
    }
}
