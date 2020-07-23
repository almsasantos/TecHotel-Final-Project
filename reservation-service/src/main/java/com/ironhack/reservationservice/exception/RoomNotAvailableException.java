package com.ironhack.reservationservice.exception;

public class RoomNotAvailableException extends RuntimeException {
    /**
     * Throws an Exception if a matching criteria is not met.
     * @param message Throws a custom message to the user.
     */
    public RoomNotAvailableException(String message) {
        super(message);
    }
}
