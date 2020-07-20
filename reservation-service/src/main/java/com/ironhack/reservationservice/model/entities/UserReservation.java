package com.ironhack.reservationservice.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.reservationservice.model.enums.TypeOfUser;

import javax.persistence.*;

@Entity
@Table(name = "user_reservation")
public class UserReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String userName;
    private TypeOfUser typeOfUser;
    @OneToOne(mappedBy = "userReservation")
    @JsonIgnore
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
