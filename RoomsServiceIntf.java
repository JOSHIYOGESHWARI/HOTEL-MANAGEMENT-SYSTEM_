package com.hotel.service;

import java.util.List;

import com.hotel.entity.Rooms;
import com.hotel.exception.RoomNotFoundException;

public interface RoomsServiceIntf {

	// Retrieves a room by its room ID.
	Rooms getRoomByRoomId(int roomId) throws RoomNotFoundException;

	// Retrieves all rooms available in the system.
	List<Rooms> getAllRooms();

	// Retrieves rooms based on their status (either available or unavailable).
	List<Rooms> getRoomByStatus(boolean status);
}
