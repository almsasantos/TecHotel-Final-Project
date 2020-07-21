package com.ironhack.invoiceservice.model.viewmodel;

import com.ironhack.invoiceservice.enums.RoomType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RoomReservationViewModel {
    @NotNull
    private Long userId;
    @NotNull
    private String userName;
    @NotNull
    private Integer roomId;
    @NotNull
    private RoomType roomType;
    @NotNull
    @Min(value = 1, message = "Number of nights must be more than zero")
    private Integer numberOfNights;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Integer getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(Integer numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
}
