package com.hotel.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotel.controller.RoomTypeController;
import com.hotel.entity.RoomType;
import com.hotel.entity.Rooms;
import com.hotel.exception.RoomTypeNotFoundException;
import com.hotel.service.RoomTypeServiceIntf;

class RoomTypeControllerTest {

	// Mocking the RoomTypeService interface to simulate its behavior
	@Mock
	private RoomTypeServiceIntf roomTypeServiceIntf;

	// Injecting the mocked RoomTypeService into the RoomTypeController
	@InjectMocks
	private RoomTypeController roomTypeController;

	// Setup method to initialize mocks before each test
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initializes the mocks for each test
	}

	/**
	 * Test case for retrieving all room types. It ensures that the controller
	 * correctly returns all room types.
	 */
	@Test
	void testGetAllRoomTypes() {
		// Creating mock RoomType entities
		RoomType roomType1 = new RoomType(1, "Single", 100.0, null);
		RoomType roomType2 = new RoomType(2, "Double", 150.0, null);

		// Mocking the service layer method call to return a list of room types
		List<RoomType> roomTypesList = Arrays.asList(roomType1, roomType2);
		when(roomTypeServiceIntf.getAllRoomTypes()).thenReturn(roomTypesList);

		// Calling the controller method and capturing the response
		ResponseEntity<List<RoomType>> response = roomTypeController.getAllRoomTypes();

		// Asserting the response status code is OK
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the response body contains the expected number of room types
		assertEquals(2, response.getBody().size());

		// Asserting that the first room type ID is 1 and name is "Single"
		assertEquals(1, response.getBody().get(0).getRoomTypeId());
		assertEquals("Single", response.getBody().get(0).getRoomTypeName());

		// Asserting that the second room type ID is 2 and name is "Double"
		assertEquals(2, response.getBody().get(1).getRoomTypeId());
		assertEquals("Double", response.getBody().get(1).getRoomTypeName());
	}

	/**
	 * Test case for retrieving a room type by its ID. It ensures that the
	 * controller returns the correct room type details when a valid ID is provided.
	 * 
	 * @throws RoomTypeNotFoundException if the room type is not found
	 */
	@Test
	void testGetRoomTypeById() throws RoomTypeNotFoundException {
		// Creating a mock RoomType entity
		RoomType roomType = new RoomType(1, "Single", 100.0, null);

		// Mocking the service layer method call to return the mock room type when room
		// type ID 1 is passed
		when(roomTypeServiceIntf.getRoomTypeById(1)).thenReturn(roomType);

		// Calling the controller method and capturing the response
		ResponseEntity<RoomType> response = roomTypeController.getRoomTypeById(1);

		// Asserting the response status code is OK
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the room type ID and name returned in the response match the
		// expected values
		assertEquals(1, response.getBody().getRoomTypeId());
		assertEquals("Single", response.getBody().getRoomTypeName());
	}

	/**
	 * Test case for retrieving rooms by room type ID. It ensures that the
	 * controller correctly returns the list of rooms associated with the given room
	 * type ID.
	 * 
	 * @throws RoomTypeNotFoundException if the room type is not found
	 */
	@Test
	void testGetRoomsForRoomType() throws RoomTypeNotFoundException {
		// Creating mock Rooms entities
		Rooms room1 = new Rooms(1, true, null);
		Rooms room2 = new Rooms(2, false, null);

		// Mocking the service layer method call to return a list of rooms for room type
		// ID 1
		List<Rooms> roomsList = Arrays.asList(room1, room2);
		when(roomTypeServiceIntf.getRoomsForRoomType(1)).thenReturn(roomsList);

		// Calling the controller method and capturing the response
		ResponseEntity<List<Rooms>> response = roomTypeController.getRoomsforRoomType(1);

		// Asserting the response status code is OK
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the response body contains the expected number of rooms
		assertEquals(2, response.getBody().size());

		// Asserting that the first room has ID 1 and status true
		assertEquals(1, response.getBody().get(0).getRoomId());
		assertEquals(true, response.getBody().get(0).isStatus());

		// Asserting that the second room has ID 2 and status false
		assertEquals(2, response.getBody().get(1).getRoomId());
		assertEquals(false, response.getBody().get(1).isStatus());
	}

}