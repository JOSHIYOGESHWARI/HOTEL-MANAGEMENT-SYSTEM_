package com.hotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entity.RoomBooking;

/**
 * Repository interface for managing RoomBooking entities. Provides methods to
 * interact with the RoomBooking data in the database.
 */
@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Integer> {

	// Finds all room bookings associated with a specific room ID.
	
	List<RoomBooking> findByRooms_RoomId(int roomId);

	// Finds all room bookings with a checkout date before the specified date.
	
	List<RoomBooking> findByCheckOutDateBefore(LocalDate currentDate);
}
