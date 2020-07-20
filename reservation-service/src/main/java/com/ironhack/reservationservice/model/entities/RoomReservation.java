package com.ironhack.reservationservice.model.entities;

import com.ironhack.reservationservice.model.classes.Money;
import com.ironhack.reservationservice.model.enums.RoomType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "room_reservations")
public class RoomReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomReservationId;
    private Integer roomId;
    private RoomType roomType;
    private LocalDate checkInDay;
    private Integer numberOfNights;
    private LocalDate checkOutDay;
    private Money totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
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
