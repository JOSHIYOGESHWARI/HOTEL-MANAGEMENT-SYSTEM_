package com.hotel.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.dto.Customer;
import com.hotel.entity.RoomBooking;
import com.hotel.exception.RoomBookingNotFoundException;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.exception.CustomerNotFoundException;

public interface RoomBookingServiceIntf {

	// Creates a new room booking.
	RoomBooking createBooking(RoomBooking roomBooking);

	// Retrieves a room booking by its ID.
	RoomBooking getBookingById(int bookingId) throws RoomBookingNotFoundException;

	// Retrieves all the room bookings.
	List<RoomBooking> getAllBookings();

	// Retrieves room bookings associated with a specific room by room ID.
	List<RoomBooking> getBookingByRoomId(int roomId) throws RoomNotFoundException;

	// Checks for expired bookings, updates room status to "available", and delete
	// the expired bookings.
	String checkOutAndUpdateRoomStatus();

	
	//Feign Client method to fetch customer details by customer ID.
	ResponseEntity<Customer> findCustomerById(@PathVariable int customerId) throws CustomerNotFoundException;

}
