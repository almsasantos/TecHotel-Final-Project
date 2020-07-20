package com.ironhack.edgeservice.model.entities.reservations;

import com.ironhack.edgeservice.model.classes.Money;
import com.ironhack.edgeservice.model.enums.RoomType;

import java.time.LocalDate;

public class RoomReservation {
    private Long roomReservationId;
    private Integer roomId;
    private RoomType roomType;
    private LocalDate checkInDay;
    private Integer numberOfNights;
    private LocalDate checkOutDay;
    private Money totalPrice;
    private UserReservation userReservation;

    public RoomReservation() {
        this.checkInDay = LocalDate.now();
    }

    public RoomReservation(Integer roomId, RoomType roomType, Integer numberOfNights) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.checkInDay = LocalDate.now();
        this.numberOfNights = numberOfNights;
        this.checkOutDay = null;
        this.totalPrice = null;
    }

    public Long getRoomReservationId() {
        return roomReservationId;
    }

    public void setRoomReservationId(Long roomReservationId) {
        this.roomReservationId = roomReservationId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(Integer numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getCheckInDay() {
        return checkInDay;
    }

    public void setCheckInDay(LocalDate checkInDay) {
        this.checkInDay = checkInDay;
    }

    public LocalDate getCheckOutDay() {
        return checkOutDay;
    }

    public void setCheckOutDay(LocalDate checkOutDay) {
        this.checkOutDay = checkOutDay;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public UserReservation getUserReservation() {
        return userReservation;
    }

    public void setUserReservation(UserReservation userReservation) {
        this.userReservation = userReservation;
    }
}
