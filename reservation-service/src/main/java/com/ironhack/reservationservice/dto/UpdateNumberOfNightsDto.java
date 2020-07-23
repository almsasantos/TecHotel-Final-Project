package com.ironhack.reservationservice.dto;

/**
 * Update Number of Nights Dto
 */
public class UpdateNumberOfNightsDto {
    /**
     * Attribute userId of type Long
     */
    private Long userId;
    /**
     * Attribute numberOfNights of type Integer
     */
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
