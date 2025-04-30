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

import com.hotel.controller.RoomBookingController;
import com.hotel.dto.Customer;
import com.hotel.entity.RoomBooking;
import com.hotel.exception.CustomerNotFoundException;
import com.hotel.exception.RoomBookingNotFoundException;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.service.RoomBookingServiceIntf;

class RoomBookingControllerTest {

	// Mocking the RoomBookingServiceIntf to simulate its behavior
	@Mock
	private RoomBookingServiceIntf roomBookingServiceIntf;

	// Injecting the mocked service into the RoomBookingController
	@InjectMocks
	private RoomBookingController roomBookingController;

	// Setup method to initialize mocks before each test
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Initializes the mocks before each test
	}

	/**
	 * Test case for creating a new room booking. It ensures that the controller
	 * correctly creates a new booking and returns the appropriate response.
	 */
	@Test
	void testCreateBooking() {
		// Creating a mock RoomBooking entity to simulate the request body
		RoomBooking roomBooking = new RoomBooking(1, true, null, null, 100.0, 123, null);

		// Mocking the service layer method call to return the created booking
		when(roomBookingServiceIntf.createBooking(roomBooking)).thenReturn(roomBooking);

		// Calling the controller method and capturing the response
		ResponseEntity<RoomBooking> response = roomBookingController.createBooking(roomBooking);

		// Asserting the response status code is CREATED (201)
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		// Asserting that the response body contains the expected room booking details
		assertEquals(1, response.getBody().getBookingId());
		assertEquals(100.0, response.getBody().getPrice());
	}

	/**
	 * Test case for retrieving a room booking by booking ID. It ensures that the
	 * controller correctly returns the booking details for a valid booking ID.
	 * 
	 * @throws RoomBookingNotFoundException if the booking is not found
	 */
	@Test
	void testGetBookingById() throws RoomBookingNotFoundException {
		// Creating a mock RoomBooking entity to simulate the returned booking
		RoomBooking roomBooking = new RoomBooking(1, true, null, null, 100.0, 123, null);

		// Mocking the service layer method call to return the mock booking when booking
		// ID 1 is passed
		when(roomBookingServiceIntf.getBookingById(1)).thenReturn(roomBooking);

		// Calling the controller method and capturing the response
		ResponseEntity<RoomBooking> response = roomBookingController.getBookingById(1);

		// Asserting the response status code is OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the booking ID and price returned in the response match the
		// expected values
		assertEquals(1, response.getBody().getBookingId());
		assertEquals(100.0, response.getBody().getPrice());
	}

	/**
	 * Test case for retrieving all room bookings. It ensures that the controller
	 * correctly returns all room bookings.
	 */
	@Test
	void testGetAllBookings() {
		// Creating mock RoomBooking entities to simulate the response
		RoomBooking roomBooking1 = new RoomBooking(1, true, null, null, 100.0, 123, null);
		RoomBooking roomBooking2 = new RoomBooking(2, false, null, null, 150.0, 124, null);

		// Mocking the service layer method call to return a list of room bookings
		List<RoomBooking> bookingsList = Arrays.asList(roomBooking1, roomBooking2);
		when(roomBookingServiceIntf.getAllBookings()).thenReturn(bookingsList);

		// Calling the controller method and capturing the response
		ResponseEntity<List<RoomBooking>> response = roomBookingController.getAllBookings();

		// Asserting the response status code is OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the response body contains the expected number of bookings
		assertEquals(2, response.getBody().size());

		// Asserting the details of the first booking in the response
		assertEquals(1, response.getBody().get(0).getBookingId());
		assertEquals(100.0, response.getBody().get(0).getPrice());

		// Asserting the details of the second booking in the response
		assertEquals(2, response.getBody().get(1).getBookingId());
		assertEquals(150.0, response.getBody().get(1).getPrice());
	}

	/**
	 * Test case for retrieving room bookings by room ID. It ensures that the
	 * controller correctly returns bookings for a given room ID.
	 * 
	 * @throws RoomNotFoundException if no bookings exist for the room ID
	 */
	@Test
	void testGetBookingByRoomId() throws RoomNotFoundException {
		// Creating mock RoomBooking entities to simulate the response
		RoomBooking roomBooking1 = new RoomBooking(1, true, null, null, 100.0, 123, null);
		RoomBooking roomBooking2 = new RoomBooking(2, false, null, null, 150.0, 124, null);

		// Mocking the service layer method call to return a list of bookings for room
		// ID 1
		List<RoomBooking> bookingsList = Arrays.asList(roomBooking1, roomBooking2);
		when(roomBookingServiceIntf.getBookingByRoomId(1)).thenReturn(bookingsList);

		// Calling the controller method and capturing the response
		ResponseEntity<List<RoomBooking>> response = roomBookingController.getBookingByRoomId(1);

		// Asserting the response status code is OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the response body contains the expected number of bookings
		assertEquals(2, response.getBody().size());

		// Asserting the details of the first booking
		assertEquals(1, response.getBody().get(0).getBookingId());
		assertEquals(100.0, response.getBody().get(0).getPrice());

		// Asserting the details of the second booking
		assertEquals(2, response.getBody().get(1).getBookingId());
		assertEquals(150.0, response.getBody().get(1).getPrice());
	}

	/**
	 * Test case for checking out and updating the room status. It ensures that the
	 * controller correctly calls the service to handle the room checkout and status
	 * update.
	 */
	@Test
	void testCheckOutAndUpdateRoomStatus() {
		// Mocking the service layer method call to return a success message
		when(roomBookingServiceIntf.checkOutAndUpdateRoomStatus()).thenReturn("status is updated successfully");

		// Calling the controller method and capturing the response
		ResponseEntity<String> response = roomBookingController.checkOutAndUpdateRoomStatus();

		// Asserting the response status code is OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the response body contains the expected message
		assertEquals("status is updated successfully", response.getBody());
	}

	/**
	 * Test case for retrieving a customer by customer ID using Feign client. It
	 * ensures that the controller correctly calls the Feign client and returns the
	 * customer details.
	 * 
	 * @throws CustomerNotFoundException if the customer is not found
	 */
	@Test
	void testFindCustomerById() throws CustomerNotFoundException {
		// Creating a mock Customer entity
		Customer customer = new Customer(123, "John", "Doe", "johndoe@example.com", "123 Street", null);

		// Mocking the service layer method call to return the mock customer
		when(roomBookingServiceIntf.findCustomerById(123)).thenReturn(ResponseEntity.ok(customer));

		// Calling the controller method and capturing the response
		ResponseEntity<Customer> response = roomBookingController.findCustomerById(123);

		// Asserting the response status code is OK (200)
		assertEquals(HttpStatus.OK, response.getStatusCode());

		// Asserting that the response body contains the expected customer details
		assertEquals(123, response.getBody().getCustomerId());
		assertEquals("John", response.getBody().getFirstName());
	}
}
