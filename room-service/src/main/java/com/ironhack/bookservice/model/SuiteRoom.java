package com.ironhack.bookservice.model;

import com.ironhack.bookservice.enums.RoomType;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Suite's Room Table
 */
@Entity
@Table(name = "suites")
@PrimaryKeyJoinColumn(name = "roomId")
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
