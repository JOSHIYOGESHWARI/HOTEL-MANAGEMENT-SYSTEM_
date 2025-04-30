package com.hotel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.entity.CustomerBookings;
import com.hotel.exception.BookingNotFoundException;
import com.hotel.repository.CustomerBookingsRepository;
import com.hotel.service.CustomerBookingServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookingsServiceTesting {

	
	  @Mock
	    private CustomerBookingsRepository customerbookingrepo;

	    @InjectMocks
	    private CustomerBookingServiceImpl customerBookingService;

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
	        when(customerbookingrepo.save(any(CustomerBookings.class))).thenReturn(booking);
	        CustomerBookings savedBooking = customerBookingService.addBooking(booking);
	        assertEquals(booking, savedBooking);
	    }

	    @Test
	    void testUpdateBooking() throws BookingNotFoundException {
	        when(customerbookingrepo.findById(booking.getBookingId())).thenReturn(Optional.of(booking));
	        when(customerbookingrepo.save(any(CustomerBookings.class))).thenReturn(booking);
	        CustomerBookings updatedBooking = customerBookingService.updateBooking(booking);
	        assertEquals(booking, updatedBooking);
	    }

	    @Test
	    void testGetBookingById() throws BookingNotFoundException {
	        when(customerbookingrepo.findById(booking.getBookingId())).thenReturn(Optional.of(booking));
	        CustomerBookings foundBooking = customerBookingService.getBookingById(booking.getBookingId());
	        assertEquals(booking, foundBooking);
	    }

	    @Test
	    void testGetAllBookings() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(customerbookingrepo.findAll()).thenReturn(bookings);
	        List<CustomerBookings> allBookings = customerBookingService.getAllBookings();
	        assertEquals(bookings, allBookings);
	    }
//
//	    @Test
//	    void testRemoveBooking() throws BookingNotFoundException {
//	        customerBookingService.removeBooking(booking.getBookingId());
//	        verify(customerbookingrepo).deleteById(booking.getBookingId());
//	    }

	    @Test
	    void testFindByCheckoutDate() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(customerbookingrepo.findByCheckoutDate(booking.getCheckoutDate())).thenReturn(bookings);
	        List<CustomerBookings> foundBookings = customerBookingService.findByCheckoutDate(booking.getCheckoutDate());
	        assertEquals(bookings, foundBookings);
	    }

	    @Test
	    void testFindByCheckinDate() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(customerbookingrepo.findByCheckinDate(booking.getCheckinDate())).thenReturn(bookings);
	        List<CustomerBookings> foundBookings = customerBookingService.findByCheckinDate(booking.getCheckinDate());
	        assertEquals(bookings, foundBookings);
	    }

	    @Test
	    void testCalculateRevenue() {
	        BigDecimal revenue = new BigDecimal("5000");
	        when(customerbookingrepo.calculateRevenue()).thenReturn(revenue);
	        BigDecimal calculatedRevenue = customerBookingService.calculateRevenue();
	        assertEquals(revenue, calculatedRevenue);
	    }

	    @Test
	    void testGetAllBookingByCustName() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(customerbookingrepo.findByCustomer_FirstName("John")).thenReturn(bookings);
	        List<CustomerBookings> foundBookings = customerBookingService.getAllBookingByCustName("John");
	        assertEquals(bookings, foundBookings);
	    }

	    @Test
	    void testGetByCustEmail() {
	        List<CustomerBookings> bookings = Arrays.asList(booking);
	        when(customerbookingrepo.findByCustomer_Email("john@example.com")).thenReturn(bookings);
	        List<CustomerBookings> foundBookings = customerBookingService.getByCustEmail("john@example.com");
	        assertEquals(bookings, foundBookings);
	    }
}
