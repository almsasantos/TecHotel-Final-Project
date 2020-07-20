package com.ironhack.reservationservice.dto;

public class UpdateNumberOfNightsDto {
    private Long userId;
    private Integer numberOfNights;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(Integer numberOfNights) {
        this.numberOfNights = numberOfNights;
    }
}
