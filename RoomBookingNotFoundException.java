package com.hotel.exception;

/**
 * Custom exception class to handle cases where a RoomBooking is not found. This
 * exception is thrown when an operation cannot find a room booking based on a
 * given booking ID or other criteria.
 */
public class RoomBookingNotFoundException extends Exception {

	// Constructor to create a new RoomBookingNotFoundException with a custom message.
	public RoomBookingNotFoundException(String message) {
		super(message); // Pass the message to the parent Exception class constructor
	}
}
