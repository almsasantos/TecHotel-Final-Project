package com.ironhack.edgeservice.exceptions;

/**
 * Exception of Security Service
 */
public class SecurityMicroserviceFail extends RuntimeException {
    /**
     * Constructor for Exception
     * @param message String message
     */
    public SecurityMicroserviceFail(String message) {
        super(message);
    }
}
