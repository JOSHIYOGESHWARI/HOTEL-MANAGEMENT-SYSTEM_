package com.hotel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotel.controller.CustomerBookingsController;
import com.hotel.entity.CustomerBookings;
import com.hotel.exception.BookingNotFoundException;
import com.hotel.service.CustomerBookingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookingsControllerTesting {

	   @Mock
	    private CustomerBookingServiceImpl bookingService;

	    @InjectMocks
	    private CustomerBookingsController bookingsController;

	    CustomerBookings booking;

	    @BeforeEach
	    void setUp() {
	        booking = new CustomerBookings();
	        booking.setBookingId(1);
	        booking.setRoomId(101);
	        booking.setCheckinDate(Date.valueOf("2025-03-15"));
	        booking.setCheckoutDate(Date.valueOf("2025-03-20"));
	        booking.setTotalPrice(5000);
	        booking.setStatus(true);
	    }

	    @Test
	    void testAddBooking() {
	        when(bookingService.addBooking(any(CustomerBookings.class))).thenReturn(booking);
	        ResponseEntity<CustomerBookings> response = bookingsController.addBooking(booking);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(booking, response.getBody());
	    }

	    @Test
	    void testUpdateBooking() throws BookingNotFoundException {
	        when(bookingService.updateBooking(any(CustomerBookings.class))).thenReturn(booking);
	        ResponseEntity<CustomerBookings> response = bookingsController.updateBooking(1, booking);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(booking, response.getBody());
	    }

	    @Test
	    void testGetBookingById() throws BookingNotFoundException {
	        when(bookingService.getBookingById(anyInt())).thenReturn(booking);
	        ResponseEntity<CustomerBookings> response = bookingsController.getBookingById(1);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(booking, response.getBody());
	    }

	    @Test
	    void testGetAllBookings() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(bookingService.getAllBookings()).thenReturn(bookings);
	        ResponseEntity<List<CustomerBookings>> response = bookingsController.getAllBookings();
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(bookings, response.getBody());
	    }

	    @Test
	    void testRemoveBooking() throws BookingNotFoundException {
	        when(bookingService.removeBooking(anyInt())).thenReturn("Booking deleted");
	        ResponseEntity<String> response = bookingsController.removeBooking(1);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Booking deleted", response.getBody());
	    }

	    @Test
	    void testFindByCheckoutDate() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(bookingService.findByCheckoutDate(any(Date.class))).thenReturn(bookings);
	        ResponseEntity<List<CustomerBookings>> response = bookingsController.findByCheckoutDate(Date.valueOf("2025-03-20"));
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(bookings, response.getBody());
	    }

	    @Test
	    void testFindByCheckinDate() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(bookingService.findByCheckinDate(any(Date.class))).thenReturn(bookings);
	        ResponseEntity<List<CustomerBookings>> response = bookingsController.findByCheckinDate(Date.valueOf("2025-03-15"));
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(bookings, response.getBody());
	    }

	    @Test
	    void testCalculateRevenue() {
	        BigDecimal revenue = new BigDecimal("5000");
	        when(bookingService.calculateRevenue()).thenReturn(revenue);
	        ResponseEntity<BigDecimal> response = bookingsController.calculateRevenue();
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(revenue, response.getBody());
	    }

	    @Test
	    void testGetAllBookingByCustName() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(bookingService.getAllBookingByCustName(anyString())).thenReturn(bookings);
	        ResponseEntity<List<CustomerBookings>> response = bookingsController.getAllBookingByCustName("John");
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(bookings, response.getBody());
	    }

	    @Test
	    void testGetByCustEmail() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(bookingService.getByCustEmail(anyString())).thenReturn(bookings);
	        ResponseEntity<List<CustomerBookings>> response = bookingsController.getByCustEmail("john@example.com");
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(bookings, response.getBody());
	    }
}
