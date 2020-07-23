package com.ironhack.reservationservice.model.viewmodel;

import com.ironhack.reservationservice.model.enums.RoomType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Room Reservation View Model
 */
public class RoomReservationViewModel {
    /**
     * Attribute userId of type Long
     */
    @NotNull(message = "User Id cannot be null")
    private Long userId;
    /**
     * Attribute userId of type Long
     */
    @NotNull(message = "User Name cannot be null")
    private String userName;
    /**
     * Attribute userId of type Long
     */
    @NotNull(message = "Room Id cannot be null")
    private Integer roomId;
    /**
     * Attribute userId of type Long
     */
    @NotNull(message = "Room Type Id cannot be null")
    private RoomType roomType;
    /**
     * Attribute userId of type Long
     */
    @NotNull
    @Min(value = 1, message = "Number of nights must be more than zero")
    private Integer numberOfNights;

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
     * Getter of userName
     * @return a String with userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter of userName
     * @param userName receives a String with userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
}
