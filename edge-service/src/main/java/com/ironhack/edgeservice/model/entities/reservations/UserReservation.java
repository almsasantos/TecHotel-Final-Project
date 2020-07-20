package com.ironhack.edgeservice.model.entities.reservations;

import com.ironhack.edgeservice.model.enums.TypeOfUser;

public class UserReservation {
    private Long id;
    private Long userId;
    private String userName;
    private TypeOfUser typeOfUser;
    private RoomReservation roomReservation;

    public UserReservation() {}

    public UserReservation(Long userId, String userName, TypeOfUser typeOfUser) {
        this.userId = userId;
        this.userName = userName;
        this.typeOfUser = typeOfUser;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    public void setTypeOfUser(TypeOfUser typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public RoomReservation getRoomReservation() {
        return roomReservation;
    }

    public void setRoomReservation(RoomReservation roomReservation) {
        this.roomReservation = roomReservation;
    }
}
