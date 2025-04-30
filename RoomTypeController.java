package com.hotel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entity.RoomType;
import com.hotel.entity.Rooms;
import com.hotel.exception.RoomTypeNotFoundException;
import com.hotel.service.RoomTypeServiceIntf;

@RestController
@RequestMapping("/roomtype")
public class RoomTypeController {

	// Constructor Injection
	private final RoomTypeServiceIntf roomTypeServiceIntf;

	public RoomTypeController(RoomTypeServiceIntf roomTypeServiceIntf) {
		this.roomTypeServiceIntf = roomTypeServiceIntf;
	}

	// This endpoint retrieves all available room types
	@GetMapping
	public ResponseEntity<List<RoomType>> getAllRoomTypes() {
		// Fetch the list of all room types from the service
		List<RoomType> roomTypes = roomTypeServiceIntf.getAllRoomTypes();
		// Return the list of room types in the response with OK status
		return new ResponseEntity<>(roomTypes, HttpStatus.OK);
	}

	// This endpoint retrieves a specific room type by its ID
	@GetMapping("/id/{roomTypeId}")
	public ResponseEntity<RoomType> getRoomTypeById(@PathVariable int roomTypeId) throws RoomTypeNotFoundException {
		// Fetch the room type by ID from the service
		RoomType roomType = roomTypeServiceIntf.getRoomTypeById(roomTypeId);
		// Return the room type in the response with OK status
		return new ResponseEntity<>(roomType, HttpStatus.OK);
	}

	// This endpoint retrieves the rooms associated with a specific room type
	@GetMapping("/getrooms/{roomTypeId}")
	public ResponseEntity<List<Rooms>> getRoomsforRoomType(@PathVariable int roomTypeId)
			throws RoomTypeNotFoundException {
		// Fetch rooms for a specific room type by its ID from the service
		List<Rooms> rooms = roomTypeServiceIntf.getRoomsForRoomType(roomTypeId);
		// Return the list of rooms in the response with OK status
		return new ResponseEntity<>(rooms, HttpStatus.OK);
	}
}
