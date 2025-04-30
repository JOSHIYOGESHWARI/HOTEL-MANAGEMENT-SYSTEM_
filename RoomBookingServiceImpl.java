package com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.dto.Customer;
import com.hotel.entity.RoomBooking;
import com.hotel.entity.Rooms;
import com.hotel.exception.CustomerNotFoundException;
import com.hotel.exception.RoomBookingNotFoundException;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.feignclient.CustomerFeignClient;
import com.hotel.repository.RoomBookingRepository;
import com.hotel.repository.RoomsRepository;

@Service
public class RoomBookingServiceImpl implements RoomBookingServiceIntf {

	// Dependency injection of required repositories and feign client

	// Constructor Injection
	private final RoomBookingRepository roomBookingRepository;
	private final RoomsRepository roomsRepository;
	private final CustomerFeignClient customerFeign;

	public RoomBookingServiceImpl(RoomBookingRepository roomBookingRepository, RoomsRepository roomsRepository,
			CustomerFeignClient customerFeign) {
		this.roomBookingRepository = roomBookingRepository;
		this.roomsRepository = roomsRepository;
		this.customerFeign = customerFeign;
	}

	// Creates a new booking and saves it in the database.
	@Override
	public RoomBooking createBooking(RoomBooking roomBooking) {
		return roomBookingRepository.save(roomBooking);
	}

	// Retrieves a booking by its ID.
	@Override
	public RoomBooking getBookingById(int bookingId) throws RoomBookingNotFoundException {
		return roomBookingRepository.findById(bookingId)
				.orElseThrow(() -> new RoomBookingNotFoundException("Booking with ID " + bookingId + " not found"));
	}

	// Retrieves all the bookings from the database.
	@Override
	public List<RoomBooking> getAllBookings() {
		return roomBookingRepository.findAll();
	}

	// Retrieves bookings associated with a specific room by room ID.
	@Override
	public List<RoomBooking> getBookingByRoomId(int roomId) throws RoomNotFoundException {
		List<RoomBooking> bookings = roomBookingRepository.findByRooms_RoomId(roomId);
		if (bookings.isEmpty()) {
			throw new RoomNotFoundException("Room Not Found!!!");
		}
		return bookings;
	}

	// This method checks for expired bookings, updates room status to "available",
	// and deletes the expired bookings.
	@Override
	public String checkOutAndUpdateRoomStatus() {
		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Find all room bookings with checkout date before the current date
		List<RoomBooking> expiredBookings = roomBookingRepository.findByCheckOutDateBefore(currentDate);

		// Loop through expired bookings and handle the status update and booking
		// deletion
		for (RoomBooking booking : expiredBookings) {
			Rooms room = booking.getRooms(); // Get the associated room from the booking

			// If the booking status is true (confirmed) and the room is unavailable, update
			// the room status to available
			if (booking.isStatus()) {
				room.setStatus(true); // Set the room status to available
				roomsRepository.save(room); // Save updated room status to the database
			}

			// Delete the expired room booking from the repository
			roomBookingRepository.delete(booking);
		}

		return "Status is updated successfully";
	}

	// Feign Client method to fetch customer details by customer ID.
	@Override
	public ResponseEntity<Customer> findCustomerById(int customerId) throws CustomerNotFoundException {
		return customerFeign.findCustomerById(customerId);
	}
}
