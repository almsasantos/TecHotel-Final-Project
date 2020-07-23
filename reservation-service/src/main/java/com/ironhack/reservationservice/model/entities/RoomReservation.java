package com.ironhack.reservationservice.model.entities;

import com.ironhack.reservationservice.model.classes.Money;
import com.ironhack.reservationservice.model.enums.RoomType;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Room Reservation
 */
@Entity
@Table(name = "room_reservations")
public class RoomReservation {
    /**
     * Attribute roomReservationId of type Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomReservationId;
    /**
     * Attribute roomId of type Integer
     */
    private Integer roomId;
    /**
     * Attribute roomType of type RoomType
     */
    private RoomType roomType;
    /**
     * Attribute checkInDay of type LocalDate
     */
    private LocalDate checkInDay;
    /**
     * Attribute numberOfNights of type Integer
     */
    private Integer numberOfNights;
    /**
     * Attribute checkOutDay of type LocalDate
     */
    private LocalDate checkOutDay;
    /**
     * Attribute totalPrice of type Money
     */
    private Money totalPrice;
    /**
     * Attribute userReservation of type UserReservation
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserReservation userReservation;

    /**
     * Empty RoomReservation's constructor
     */
    public RoomReservation() {
        this.checkInDay = LocalDate.now();
    }

    /**
     * Room Reservation Constructor
     * @param roomId receives an Integer with roomId
     * @param roomType receives a RoomType with roomType
     * @param numberOfNights receives an Integer with numberOfNights
     */
    public RoomReservation(Integer roomId, RoomType roomType, Integer numberOfNights) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.checkInDay = LocalDate.now();
        this.numberOfNights = numberOfNights;
        this.checkOutDay = null;
        this.totalPrice = null;
    }

    /**
     * Getter of roomReservationId
     * @return a Long with roomReservationId
     */
    public Long getRoomReservationId() {
        return roomReservationId;
    }

    /**
     * Setter of roomReservationId
     * @param roomReservationId receives a Long with roomReservationId
     */
    public void setRoomReservationId(Long roomReservationId) {
        this.roomReservationId = roomReservationId;
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
     * Getter of numberOfNights
     * @return an Integer with numberOfNights
     */
    public Integer getNumberOfNights() {
        return numberOfNights;
    }

    /**
     * Setter of numberOfNights
     * @param numberOfNights receives an Integer with numberOfNights
     */
    public void setNumberOfNights(Integer numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    /**
     * Getter of totalPrice
     * @return Money with totalPrice
     */
    public Money getTotalPrice() {
        return totalPrice;
    }

    /**
     * Setter of totalPrice
     * @param totalPrice receives Money with totalPrice
     */
    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Getter of checkInDay
     * @return a LocalDate with checkInDay
     */
    public LocalDate getCheckInDay() {
        return checkInDay;
    }

    /**
     * Setter of checkInDay
     * @param checkInDay receives a LocalDate with checkInDay
     */
    public void setCheckInDay(LocalDate checkInDay) {
        this.checkInDay = checkInDay;
    }

    /**
     * Getter of checkOutDay
     * @return a LocalDate with checkOutDay
     */
    public LocalDate getCheckOutDay() {
        return checkOutDay;
    }

    /**
     * Setter of checkOutDay
     * @param checkOutDay receives a LocalDate with checkOutDay
     */
    public void setCheckOutDay(LocalDate checkOutDay) {
        this.checkOutDay = checkOutDay;
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

    /**
     * Getter of userReservation
     * @return a UserReservation with userReservation
     */
    public UserReservation getUserReservation() {
        return userReservation;
    }

    /**
     * Setter of userReservation
     * @param userReservation receives a UserReservation with userReservation
     */
    public void setUserReservation(UserReservation userReservation) {
        this.userReservation = userReservation;
    }
}
