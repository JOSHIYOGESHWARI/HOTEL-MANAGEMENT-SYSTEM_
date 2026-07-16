package com.hotel.exception;


public class InventoryNotFoundException extends RuntimeException {
	//Constructor to initialize the exception with a specific message
    public InventoryNotFoundException(String message) {
    	// Pass the message to the parent RuntimeException class
        super(message);
    }
}
