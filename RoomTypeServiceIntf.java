package com.hotel.service;

import java.util.List;

import com.hotel.entity.RoomType;
import com.hotel.entity.Rooms;
import com.hotel.exception.RoomTypeNotFoundException;

public interface RoomTypeServiceIntf {

	// Retrieves all the room types.
	List<RoomType> getAllRoomTypes();

	// Retrieves a room type by its ID.
	RoomType getRoomTypeById(int roomTypeId) throws RoomTypeNotFoundException;

	// Retrieves the rooms associated with a specific room type.
	List<Rooms> getRoomsForRoomType(int roomTypeId) throws RoomTypeNotFoundException;
}
