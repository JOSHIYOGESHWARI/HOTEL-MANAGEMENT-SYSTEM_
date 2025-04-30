package com.hotel.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.hotel.controller.RoomsController;
import com.hotel.entity.Rooms;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.service.RoomsServiceIntf;

class RoomsControllerTest {
	
	// Mocking the RoomsService interface to simulate its behavior
	@Mock
	private RoomsServiceIntf roomsService;

	// Injecting the mocked RoomsService into the RoomsController
	@InjectMocks
	private RoomsController roomsController;

	// Setup method to initialize mocks before each test
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initializes the mocks for each test
	}

	/**
	 * Test case for retrieving a room by its ID. It ensures that the controller
	 * returns the correct room details when a valid ID is provided.
	 * 
	 * @throws RoomNotFoundException if the room is not found
	 */
	@Test
	void testGetRoomByRoomId() throws RoomNotFoundException {
		// Creating a mock room entity
		Rooms room = new Rooms(1, true, null);

		// Mocking the service layer method call to return the mock room when room ID 1
		// is passed
		when(roomsService.getRoomByRoomId(1)).thenReturn(room);

		// Calling the controller method and capturing the response
		ResponseEntity<Rooms> response = roomsController.getRoomByRoomId(1);

		// Asserting the response status code is OK
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the room ID and status returned in the response are correct
		assertEquals(1, response.getBody().getRoomId());
		assertEquals(true, response.getBody().isStatus());
	}

	/**
	 * Test case for retrieving all rooms. It ensures that the controller returns a
	 * list of all rooms.
	 */
	@Test
	void testGetAllRooms() {
		// Creating mock room entities
		Rooms room1 = new Rooms(1, true, null);
		Rooms room2 = new Rooms(2, false, null);

		// Mocking the service layer method call to return a list of rooms
		List<Rooms> roomsList = Arrays.asList(room1, room2);
		when(roomsService.getAllRooms()).thenReturn(roomsList);

		// Calling the controller method and capturing the response
		ResponseEntity<List<Rooms>> response = roomsController.getAllRooms();

		// Asserting the response status code is OK
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the response body contains the expected number of rooms
		assertEquals(2, response.getBody().size());

		// Asserting that the first and second room IDs match the expected values
		assertEquals(1, response.getBody().get(0).getRoomId());
		assertEquals(2, response.getBody().get(1).getRoomId());
	}

	/**
	 * Test case for retrieving rooms by their status. It ensures that the
	 * controller returns a list of rooms with the given status.
	 */
	@Test
	void testGetRoomByStatus() {
		// Creating mock room entities with status 'true'
		Rooms room1 = new Rooms(1, true, null);
		Rooms room2 = new Rooms(2, true, null);

		// Mocking the service layer method call to return a list of rooms with status
		// 'true'
		List<Rooms> roomsList = Arrays.asList(room1, room2);
		when(roomsService.getRoomByStatus(true)).thenReturn(roomsList);

		// Calling the controller method and capturing the response
		ResponseEntity<List<Rooms>> response = roomsController.getRoomByStatus(true);

		// Asserting the response status code is OK
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the response body contains the expected number of rooms
		assertEquals(2, response.getBody().size());

		// Asserting that the status of the rooms returned is 'true'
		assertEquals(true, response.getBody().get(0).isStatus());
		assertEquals(true, response.getBody().get(1).isStatus());
	}
}