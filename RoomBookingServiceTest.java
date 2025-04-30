package com.hotel.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.hotel.dto.Customer;
import com.hotel.entity.RoomBooking;
import com.hotel.entity.Rooms;
import com.hotel.exception.CustomerNotFoundException;
import com.hotel.exception.RoomBookingNotFoundException;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.feignclient.CustomerFeignClient;
import com.hotel.repository.RoomBookingRepository;
import com.hotel.repository.RoomsRepository;
import com.hotel.service.RoomBookingServiceImpl;

class RoomBookingServiceTest {
	// Mock dependencies of RoomBookingServiceImpl
    @Mock
    private RoomBookingRepository roomBookingRepository;
    
    @Mock
    private RoomsRepository roomsRepository;
    
    @Mock
    private CustomerFeignClient customerFeign;
    
    @InjectMocks
    private RoomBookingServiceImpl roomBookingService;

    // Initializing mocks before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case for creating a new booking.
     * It checks if the service correctly creates and saves a room booking.
     */
    @Test
    void testCreateBooking() {
        // Creating mock RoomBooking entity
        RoomBooking roomBooking = new RoomBooking(1, true, LocalDate.now(), LocalDate.now().plusDays(2), 200.0, 123, null);

        // Mocking the repository's save method to return the same roomBooking
        when(roomBookingRepository.save(roomBooking)).thenReturn(roomBooking);

        // Calling the service method to create the booking
        RoomBooking result = roomBookingService.createBooking(roomBooking);

        // Asserting that the result is the same as the input roomBooking
        assertEquals(roomBooking.getBookingId(), result.getBookingId(), "Booking ID should match");
        assertEquals(roomBooking.isStatus(), result.isStatus(), "Booking status should match");
    }

    /**
     * Test case for retrieving a booking by ID.
     * It ensures that the service correctly fetches the booking by ID.
     */
    @Test
    void testGetBookingById() throws RoomBookingNotFoundException {
        // Creating a mock RoomBooking entity
        RoomBooking roomBooking = new RoomBooking(1, true, LocalDate.now(), LocalDate.now().plusDays(2), 200.0, 123, null);

        // Mocking the repository's findById method to return the mock roomBooking
        when(roomBookingRepository.findById(1)).thenReturn(Optional.of(roomBooking));

        // Calling the service method to get the booking by ID
        RoomBooking result = roomBookingService.getBookingById(1);

        // Asserting that the returned roomBooking matches the expected one
        assertEquals(1, result.getBookingId(), "Booking ID should match");
        assertEquals(200.0, result.getPrice(), "Booking price should be 200.0");
    }

    /**
     * Test case for retrieving all bookings.
     * It ensures that the service correctly retrieves all bookings.
     */
    @Test
    void testGetAllBookings() {
        // Creating mock RoomBooking entities
        RoomBooking roomBooking1 = new RoomBooking(1, true, LocalDate.now(), LocalDate.now().plusDays(2), 200.0, 123, null);
        RoomBooking roomBooking2 = new RoomBooking(2, false, LocalDate.now().plusDays(1), LocalDate.now().plusDays(3), 150.0, 124, null);

        // Creating a list of room bookings
        List<RoomBooking> roomBookingList = Arrays.asList(roomBooking1, roomBooking2);

        // Mocking the repository's findAll method to return the list of room bookings
        when(roomBookingRepository.findAll()).thenReturn(roomBookingList);

        // Calling the service method to get all bookings
        List<RoomBooking> result = roomBookingService.getAllBookings();

        // Asserting that the list contains the correct number of bookings
        assertEquals(2, result.size(), "There should be 2 room bookings");
    }

    /**
     * Test case for retrieving bookings by room ID.
     * It ensures that the service correctly fetches bookings by room ID.
     */
    @Test
    void testGetBookingByRoomId() throws RoomNotFoundException {
        // Creating a mock RoomBooking entity associated with a room
        Rooms room = new Rooms(1, true, null);
        RoomBooking roomBooking = new RoomBooking(1, true, LocalDate.now(), LocalDate.now().plusDays(2), 200.0, 123, room);

        // Mocking the repository's findByRooms_RoomId method to return the room booking list
        when(roomBookingRepository.findByRooms_RoomId(1)).thenReturn(Arrays.asList(roomBooking));

        // Calling the service method to get bookings by room ID
        List<RoomBooking> result = roomBookingService.getBookingByRoomId(1);

        // Asserting that the list contains the correct booking associated with the room
        assertEquals(1, result.size(), "There should be 1 booking for room ID 1");
        assertEquals(1, result.get(0).getRooms().getRoomId(), "Room ID should match");
    }

    /**
     * Test case for checking out and updating room status.
     * It ensures that the service correctly updates the status of expired bookings and rooms.
     */
    @Test
    void testCheckOutAndUpdateRoomStatus() {
        // Creating a mock RoomBooking entity with an expired checkout date
        RoomBooking expiredBooking = new RoomBooking(1, true, LocalDate.now().minusDays(1), LocalDate.now().minusDays(1), 100.0, 123, null);
        
        // Mocking the repository's findByCheckOutDateBefore method to return a list of expired bookings
        when(roomBookingRepository.findByCheckOutDateBefore(LocalDate.now())).thenReturn(Arrays.asList(expiredBooking));

        // Creating a mock Rooms entity associated with the RoomBooking entity
        Rooms room = new Rooms(1, false, null);  // Assume 'false' means unavailable

        // Linking the room to the booking
        expiredBooking.setRooms(room);

        // Mocking the roomsRepository to return the mock room and save the updated status
        when(roomsRepository.save(room)).thenReturn(room);

        // Calling the service method to check out and update room status
        String result = roomBookingService.checkOutAndUpdateRoomStatus();

        // Asserting that the room status has been updated and the service method returns the correct message
        assertEquals("status is updated successfully", result, "Status update message should match");

        // Verifying that the room's status was updated to 'available' (true)
        assertEquals(true, room.isStatus(), "Room status should be updated to available");
    }

    /**
     * Test case for retrieving customer information via Feign Client.
     * It ensures that the service correctly calls the Feign client to get customer details.
     */
    @Test
    void testFindCustomerById() throws CustomerNotFoundException {
        // Creating a mock Customer entity
        Customer customer = new Customer(123, "John", "Doe", "johndoe@example.com", "123 Main St", LocalDate.of(1990, 5, 15));

        // Mocking the Feign client's findCustomerById method to return the mock customer
        when(customerFeign.findCustomerById(anyInt())).thenReturn(ResponseEntity.ok(customer));

        // Calling the service method to get the customer by ID
        ResponseEntity<Customer> result = roomBookingService.findCustomerById(123);

        // Asserting that the returned customer matches the expected one
        assertEquals("John", result.getBody().getFirstName(), "Customer first name should match");
        assertEquals("Doe", result.getBody().getLastName(), "Customer last name should match");
        assertEquals("johndoe@example.com", result.getBody().getEmail(), "Customer email should match");
    }

    /**
     * Test case for handling RoomBookingNotFoundException.
     * It ensures that the exception is thrown when no booking is found.
     */
    @Test
    void testGetBookingByIdThrowsException() {
        // Mocking the repository's findById method to return an empty Optional (booking not found)
        when(roomBookingRepository.findById(999)).thenReturn(Optional.empty());

        // Calling the service method and asserting that the exception is thrown
        try {
            roomBookingService.getBookingById(999);
        } catch (RoomBookingNotFoundException e) {
            assertEquals("Booking with ID 999 not found", e.getMessage(), "Exception message should match");
        }
    }
}
