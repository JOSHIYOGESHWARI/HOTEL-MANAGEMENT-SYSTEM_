package com.hotel.exception;

/**
 * Custom exception class to handle cases where a Room is not found. This
 * exception is thrown when an operation cannot find a room based on a given
 * room ID or other criteria, indicating that the room was not found in the
 * system.
 */
public class RoomNotFoundException extends Exception {

	// Constructor to create a new RoomNotFoundException with a custom message.

	public RoomNotFoundException(String message) {
		super(message); // Pass the message to the parent Exception class constructor
	}
}
