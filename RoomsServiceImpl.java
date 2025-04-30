package com.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.entity.Rooms;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.repository.RoomsRepository;

@Service
public class RoomsServiceImpl implements RoomsServiceIntf {

	// Constructor Injection
	private final RoomsRepository roomsRepository;

	public RoomsServiceImpl(RoomsRepository roomsRepository) {
		this.roomsRepository = roomsRepository;
	}

	// Retrieves a room by its room ID.
	@Override
	public Rooms getRoomByRoomId(int roomId) throws RoomNotFoundException {
		// Fetch the room by its ID, throwing an exception if it's not found
		return roomsRepository.findById(roomId)
				.orElseThrow(() -> new RoomNotFoundException("Room with ID " + roomId + " not found"));
	}

	// Retrieves all rooms available in the system.
	@Override
	public List<Rooms> getAllRooms() {
		// Fetch and return the list of all rooms from the repository
		return roomsRepository.findAll();
	}

	// Retrieves all rooms based on their status (either available or unavailable).
	@Override
	public List<Rooms> getRoomByStatus(boolean status) {
		// Fetch and return rooms that match the given availability status
		return roomsRepository.findByStatus(status);
	}
}
