package com.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.entity.RoomType;
import com.hotel.entity.Rooms;
import com.hotel.exception.RoomTypeNotFoundException;
import com.hotel.repository.RoomTypeRepository;

@Service
public class RoomTypeServiceImpl implements RoomTypeServiceIntf {

	// Constructor Injection
	private final RoomTypeRepository roomTypeRepository;

	public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
		this.roomTypeRepository = roomTypeRepository;
	}

	// Retrieves all room types from the database.
	@Override
	public List<RoomType> getAllRoomTypes() {
		return roomTypeRepository.findAll();
	}

	// Retrieves a room type by its ID.
	@Override
	public RoomType getRoomTypeById(int roomTypeId) throws RoomTypeNotFoundException {
		return roomTypeRepository.findById(roomTypeId)
				.orElseThrow(() -> new RoomTypeNotFoundException("Room type with ID " + roomTypeId + " not found"));
	}

	// Retrieves the rooms associated with a specific room type.
	@Override
	public List<Rooms> getRoomsForRoomType(int roomTypeId) throws RoomTypeNotFoundException {
		RoomType roomType = roomTypeRepository.findById(roomTypeId).orElse(null);

		// If the room type doesn't exist, throw an exception
		if (roomType == null) {
			throw new RoomTypeNotFoundException("Room type with ID " + roomTypeId + " not found");
		}

		// Return the rooms associated with the found room type
		return roomType.getRooms();
	}
}
