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

import com.hotel.entity.RoomType;
import com.hotel.entity.Rooms;
import com.hotel.exception.RoomTypeNotFoundException;
import com.hotel.repository.RoomTypeRepository;
import com.hotel.service.RoomTypeServiceImpl;

class RoomTypeServiceTest {

	// Mocking the RoomTypeRepository dependency for the RoomTypeServiceImpl class
    @Mock
    private RoomTypeRepository roomTypeRepository;

    // Injecting the mocked RoomTypeRepository into the RoomTypeServiceImpl
    @InjectMocks
    private RoomTypeServiceImpl roomTypeService;

    // Initializing the mocks before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes the mock objects
    }

    /**
     * Test case for retrieving all room types.
     * It checks if the list of room types is returned correctly.
     */
    @Test
    void testGetAllRoomTypes() {
        // Creating mock RoomType entities
        RoomType roomType1 = new RoomType(1, "Single", 100.0, null);
        RoomType roomType2 = new RoomType(2, "Double", 150.0, null);

        // Creating a list of room types to return
        List<RoomType> roomTypeList = Arrays.asList(roomType1, roomType2);

        // Mocking the repository's findAll method to return the list of room types
        when(roomTypeRepository.findAll()).thenReturn(roomTypeList);

        // Calling the service method to get all room types
        List<RoomType> result = roomTypeService.getAllRoomTypes();

        // Asserting that the size of the list is correct
        assertEquals(2, result.size(), "There should be 2 room types");
        assertEquals("Single", result.get(0).getRoomTypeName(), "The first room type should be 'Single'");
        assertEquals("Double", result.get(1).getRoomTypeName(), "The second room type should be 'Double'");
    }

    /**
     * Test case for retrieving a room type by its ID.
     * It checks that the correct room type is returned when the ID exists.
     */
    @Test
    void testGetRoomTypeById() throws RoomTypeNotFoundException {
        // Creating a mock RoomType entity
        RoomType roomType = new RoomType(1, "Single", 100.0, null);

        // Mocking the repository's findById method to return the mock room type
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));

        // Calling the service method to get the room type by ID
        RoomType result = roomTypeService.getRoomTypeById(1);

        // Asserting that the returned room type matches the expected one
        assertEquals(1, result.getRoomTypeId(), "Room type ID should be 1");
        assertEquals("Single", result.getRoomTypeName(), "Room type name should be 'Single'");
        assertEquals(100.0, result.getRoomTypePrice(), "Room type price should be 100.0");
    }

    /**
     * Test case for retrieving rooms for a specific room type by its ID.
     * It checks that the correct rooms are returned for the given room type ID.
     */
    @Test
    void testGetRoomsforRoomType() throws RoomTypeNotFoundException {
        // Creating a mock RoomType entity with associated Rooms
        RoomType roomType = new RoomType(1, "Single", 100.0, null);
        Rooms room1 = new Rooms(101, true, roomType);
        Rooms room2 = new Rooms(102, false, roomType);
        roomType.setRooms(Arrays.asList(room1, room2));

        // Mocking the repository's findById method to return the mock room type
        when(roomTypeRepository.findById(1)).thenReturn(Optional.of(roomType));

        // Calling the service method to get rooms for the given room type
        List<Rooms> result = roomTypeService.getRoomsForRoomType(1);

        // Asserting that the list contains the correct rooms
        assertEquals(2, result.size(), "There should be 2 rooms for room type ID 1");
        assertEquals(101, result.get(0).getRoomId(), "The first room should have ID 101");
        assertEquals(102, result.get(1).getRoomId(), "The second room should have ID 102");
    }

    /**
     * Test case for handling the scenario when a room type is not found.
     * It ensures that the appropriate exception is thrown when the room type ID does not exist.
     */
    @Test
    void testGetRoomTypeByIdThrowsException() {
        // Mocking the repository's findById method to return an empty Optional (room type not found)
        when(roomTypeRepository.findById(999)).thenReturn(Optional.empty());

        // Calling the service method and asserting that the exception is thrown
        try {
            roomTypeService.getRoomTypeById(999);
        } catch (RoomTypeNotFoundException e) {
            assertEquals("Room type with ID 999 not found", e.getMessage(), "Exception message should match");
        }
    }

    /**
     * Test case for handling the scenario when rooms are not found for a specific room type.
     * It ensures that the appropriate exception is thrown when no rooms are associated with the room type.
     */
    @Test
    void testGetRoomsforRoomTypeThrowsException() {
        // Mocking the repository's findById method to return an empty Optional (room type not found)
        when(roomTypeRepository.findById(999)).thenReturn(Optional.empty());

        // Calling the service method and asserting that the exception is thrown
        try {
            roomTypeService.getRoomsForRoomType(999);
        } catch (RoomTypeNotFoundException e) {
            assertEquals("Room type with ID 999 not found", e.getMessage(), "Exception message should match");
        }
    }
}