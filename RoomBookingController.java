package com.hotel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.Customer;
import com.hotel.entity.RoomBooking;
import com.hotel.exception.CustomerNotFoundException;
import com.hotel.exception.RoomBookingNotFoundException;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.service.RoomBookingServiceIntf;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/roombookings")
@Validated
public class RoomBookingController {
	
    //Constructor Injection
	private final RoomBookingServiceIntf roomBookingServiceIntf;
	
	public RoomBookingController(RoomBookingServiceIntf roomBookingServiceIntf) {
		this.roomBookingServiceIntf=roomBookingServiceIntf;
	}

	// This method creates a new room booking.
	@PostMapping("/add")
	public ResponseEntity<RoomBooking> createBooking(@Valid @RequestBody RoomBooking roomBooking) {
		RoomBooking createdBooking = roomBookingServiceIntf.createBooking(roomBooking);
		// Returning the created booking with a status of "CREATED"
		return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
	}

	// This method retrieves a room booking based on the booking ID.
	@GetMapping("/id/{bookingId}")
	public ResponseEntity<RoomBooking> getBookingById(@PathVariable int bookingId) throws RoomBookingNotFoundException {
		RoomBooking booking = roomBookingServiceIntf.getBookingById(bookingId);
		// Returning the booking details with status "OK"
		return new ResponseEntity<>(booking, HttpStatus.OK);
	}

	// This method retrieves all room bookings.
	@GetMapping
	public ResponseEntity<List<RoomBooking>> getAllBookings() {
		List<RoomBooking> bookings = roomBookingServiceIntf.getAllBookings();
		// Returning the list of bookings with status "OK"
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	// This method retrieves room bookings for a specific room ID.
	@GetMapping("/bookingByRoomId/{roomId}")
	public ResponseEntity<List<RoomBooking>> getBookingByRoomId(@PathVariable int roomId) throws RoomNotFoundException {
		List<RoomBooking> bookings = roomBookingServiceIntf.getBookingByRoomId(roomId);
		// Returning the list of bookings for the room
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	// This method checks out expired bookings and updates room statuses.
	@GetMapping("/update")
	public ResponseEntity<String> checkOutAndUpdateRoomStatus() {
		String response = roomBookingServiceIntf.checkOutAndUpdateRoomStatus();
		// Returning a success message after updating the room status
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// This method retrieves customer details by customer ID using Feign Client.
	@GetMapping("/findcustomerbyid/{customerid}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable int customerid) throws CustomerNotFoundException {
		return roomBookingServiceIntf.findCustomerById(customerid);
	}
}
