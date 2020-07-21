package com.ironhack.activityservice.model.rooms;

import com.ironhack.activityservice.model.enums.RoomType;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Regular Room
 */
@Embeddable
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
