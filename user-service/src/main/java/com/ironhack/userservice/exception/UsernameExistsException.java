package com.ironhack.userservice.exception;

public class UsernameExistsException extends RuntimeException {

    /**
     * Throws an Exception if a matching criteria is not met.
     * @param message Throws a custom message to the user.
     */
    public UsernameExistsException(String message) {
        super(message);
    }
}
