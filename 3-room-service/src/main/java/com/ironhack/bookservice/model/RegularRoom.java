package com.ironhack.bookservice.model;

import com.ironhack.bookservice.enums.RoomType;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Regular Room's Table
 */
@Entity
@Table(name = "regular_rooms")
@PrimaryKeyJoinColumn(name = "roomId")
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
