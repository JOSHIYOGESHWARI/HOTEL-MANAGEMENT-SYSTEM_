package com.hotel.exception;

/**
 * Custom exception class to handle cases where a RoomType is not found. This
 * exception is thrown when an operation cannot find a room type based on a
 * given room type ID or other criteria, indicating that the room type was not
 * found in the system.
 */
public class RoomTypeNotFoundException extends Exception {

	// Constructor to create a new RoomTypeNotFoundException with a custom message.

	public RoomTypeNotFoundException(String message) {
		super(message); // Pass the message to the parent Exception class constructor
	}
}
