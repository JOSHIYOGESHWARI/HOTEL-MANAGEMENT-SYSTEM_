package com.billing.exception;

/**
 * Custom exception class for handling room booking not found scenarios.
 * This exception is thrown when a room booking cannot be found.
 */
public class RoomBookingNotFoundException extends Exception {

    /**
     * Constructor for the RoomBookingNotFoundException.
     * 
     * @param message the error message associated with the exception
     */
    public RoomBookingNotFoundException(String message) {
        super(message);
    }
}
