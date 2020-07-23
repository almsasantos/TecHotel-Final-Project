package com.ironhack.reservationservice.dto;

/**
 * Update Room Reservation Dto
 */
public class UpdateRoomReservationDto {
    /**
     * Attribute userId of type Long
     */
    private Long userId;
    /**
     * Attribute userName of type String
     */
    private String userName;
    /**
     * Attribute roomId of type Integer
     */
    private Integer roomId;

    /**
     * Getter of userId
     * @return
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter of userId
     * @param userId
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
}
