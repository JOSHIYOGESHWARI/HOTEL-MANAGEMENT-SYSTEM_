package com.hotel.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hotel.entity.Rooms;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.repository.RoomsRepository;
import com.hotel.service.RoomsServiceImpl;

class RoomsServiceTest {

	// Mocking the RoomsRepository dependency for the RoomsServiceImpl class
	@Mock
	private RoomsRepository roomsRepository;

	// Injecting the mocked RoomsRepository into RoomsServiceImpl
	@InjectMocks
	private RoomsServiceImpl roomsService;

	// Initializing the mocks before each test
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initializes the mocks for this test class
	}

	/**
	 * Test case for retrieving a room by its ID. It checks that the correct room is
	 * returned when the repository finds a room by ID.
	 */
	@Test
	void testGetRoomByRoomId() throws RoomNotFoundException {
		// Creating a mock Room entity to return
		Rooms room = new Rooms(1, true, null);

		// Mocking the repository's findById method to return the mock room
		when(roomsRepository.findById(1)).thenReturn(Optional.of(room));

		// Calling the service method and asserting the returned room details
		Rooms result = roomsService.getRoomByRoomId(1);
		assertEquals(1, result.getRoomId(), "Room ID should be 1");
		assertEquals(true, result.isStatus(), "Room status should be true");
	}

	/**
	 * Test case for retrieving all rooms. It checks that all rooms are returned
	 * when the repository returns a list of rooms.
	 */
	@Test
	void testGetAllRooms() {
		// Creating mock Room entities
		Rooms room1 = new Rooms(1, true, null);
		Rooms room2 = new Rooms(2, false, null);

		// Creating a list of rooms to return
		List<Rooms> roomsList = Arrays.asList(room1, room2);

		// Mocking the repository's findAll method to return the list of rooms
		when(roomsRepository.findAll()).thenReturn(roomsList);

		// Calling the service method and asserting the returned list of rooms
		List<Rooms> result = roomsService.getAllRooms();
		assertEquals(2, result.size(), "There should be 2 rooms");
		assertEquals(1, result.get(0).getRoomId(), "The first room's ID should be 1");
		assertEquals(2, result.get(1).getRoomId(), "The second room's ID should be 2");
	}

	/**
	 * Test case for retrieving rooms by their status. It checks that only rooms
	 * with the given status are returned.
	 */
	@Test
	void testGetRoomByStatus() {
		// Creating mock Room entities with the same status
		Rooms room1 = new Rooms(1, true, null);
		Rooms room2 = new Rooms(2, true, null);

		// Creating a list of rooms with a true status
		List<Rooms> roomsList = Arrays.asList(room1, room2);

		// Mocking the repository's findByStatus method to return rooms with status
		// "true"
		when(roomsRepository.findByStatus(true)).thenReturn(roomsList);

		// Calling the service method and asserting the returned list of rooms
		List<Rooms> result = roomsService.getRoomByStatus(true);
		assertEquals(2, result.size(), "There should be 2 rooms with status true");
		assertEquals(true, result.get(0).isStatus(), "The first room's status should be true");
		assertEquals(true, result.get(1).isStatus(), "The second room's status should be true");
	}
}