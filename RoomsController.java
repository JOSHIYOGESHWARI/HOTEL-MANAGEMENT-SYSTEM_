package com.hotel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entity.Rooms;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.service.RoomsServiceIntf;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

	// Constructor Injection
	private final RoomsServiceIntf roomsServiceIntf;

	public RoomsController(RoomsServiceIntf roomsServiceIntf) {
		this.roomsServiceIntf = roomsServiceIntf;
	}

	// This endpoint retrieves a room by its ID
	@GetMapping("/id/{roomId}")
	public ResponseEntity<Rooms> getRoomByRoomId(@PathVariable int roomId) throws RoomNotFoundException {
		// Fetch room details by room ID from the service
		Rooms room = roomsServiceIntf.getRoomByRoomId(roomId);
		// Return the room details in the response with OK status
		return new ResponseEntity<>(room, HttpStatus.OK);
	}

	// This endpoint retrieves all rooms available in the system
	@GetMapping
	public ResponseEntity<List<Rooms>> getAllRooms() {
		// Fetch the list of all rooms from the service
		List<Rooms> rooms = roomsServiceIntf.getAllRooms();
		// Return the list of rooms in the response with OK status
		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}

	// This endpoint retrieves rooms based on their availability status (true or
	// false)
	@GetMapping("status/{status}")
	public ResponseEntity<List<Rooms>> getRoomByStatus(@PathVariable boolean status) {
		// Fetch rooms based on their availability status
		List<Rooms> rooms = roomsServiceIntf.getRoomByStatus(status);
		// Return the list of rooms with the given status in the response with OK status
		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}
}
