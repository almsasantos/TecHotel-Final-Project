package com.ironhack.reservationservice.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.reservationservice.model.enums.TypeOfUser;

import javax.persistence.*;

/**
 * UserReservation
 */
@Entity
@Table(name = "user_reservation")
public class UserReservation {
    /**
     * Attribute id of type Long
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Attribute userId of type Long
     */
    private Long userId;
    /**
     * Attribute userName of type String
     */
    private String userName;
    /**
     * Attribute typeOfUser of type TypeOfUser
     */
    private TypeOfUser typeOfUser;
    /**
     * Attribute roomReservation of type RoomReservation
     */
    @OneToOne(mappedBy = "userReservation")
    @JsonIgnore
    private RoomReservation roomReservation;

    /**
     * Empty UserReservation's Constructor
     */
    public UserReservation() {}

    /**
     * UserReservation Constructor
     * @param userId receives a Long with userId
     * @param userName receives a String with userName
     * @param typeOfUser receives a TypeOfUser with typeOfUser
     */
    public UserReservation(Long userId, String userName, TypeOfUser typeOfUser) {
        this.userId = userId;
        this.userName = userName;
        this.typeOfUser = typeOfUser;
    }

    /**
     * Getter of userId
     * @return a Long with userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter of userId
     * @param userId receives a Long with userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Getter of typeOfUser
     * @return a TypeOfUser with typeOfUser
     */
    public TypeOfUser getTypeOfUser() {
        return typeOfUser;
    }

    /**
     * Setter of typeOfUser
     * @param typeOfUser receives a TypeOfUser with typeOfUser
     */
    public void setTypeOfUser(TypeOfUser typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    /**
     * Getter of roomReservation
     * @return a RoomReservation with roomReservation
     */
    public RoomReservation getRoomReservation() {
        return roomReservation;
    }

    /**
     * Setter of roomReservation
     * @param roomReservation receives a RoomReservation with roomReservation
     */
    public void setRoomReservation(RoomReservation roomReservation) {
        this.roomReservation = roomReservation;
    }
}
