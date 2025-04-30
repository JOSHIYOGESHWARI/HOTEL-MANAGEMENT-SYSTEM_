package com.hotel.exception;

/**
 * This exception is thrown when a customer cannot be found in the system. 
 * It is used to handle cases where the customer ID or other identifying information 
 * does not match any records in the database.
 */
public class CustomerNotFoundException extends Exception {

    /**
     * Constructor for the exception. It accepts a message that describes 
     * the reason for the exception. This message is passed to the superclass 
     * to help identify the issue when the exception is thrown.
     */
    public CustomerNotFoundException(String message) {
        super(message); // Passes the message to the parent Exception class
    }
}
