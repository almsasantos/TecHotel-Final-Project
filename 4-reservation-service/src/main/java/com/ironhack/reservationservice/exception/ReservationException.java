package com.ironhack.reservationservice.exception;

public class ReservationException extends RuntimeException {
    /**
     * Throws an Exception if a matching criteria is not met.
     * @param message Throws a custom message to the user.
     */
    public ReservationException(String message) {
        super(message);
    }
}
