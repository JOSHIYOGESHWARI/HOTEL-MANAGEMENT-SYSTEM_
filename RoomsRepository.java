package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entity.RoomType;
import com.hotel.entity.Rooms;

/**
 * Repository interface for managing Rooms entities. Provides methods to
 * interact with the Rooms data in the database.
 */
@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer> {

	// Finds all rooms based on their availability status.

	List<Rooms> findByStatus(boolean status);

	// Finds rooms associated with a specific room type and their availability status.

	List<Rooms> findByRoomTypeAndStatus(RoomType roomType, boolean status);
}
