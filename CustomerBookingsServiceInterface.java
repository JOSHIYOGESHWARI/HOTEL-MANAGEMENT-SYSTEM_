package com.hotel.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.hotel.entity.CustomerBookings;
import com.hotel.exception.BookingNotFoundException;

public interface CustomerBookingsServiceInterface {

	CustomerBookings addBooking(CustomerBookings booking);

    // Update an existing booking
    CustomerBookings updateBooking(CustomerBookings booking) throws BookingNotFoundException;

    // Get a booking by its ID
    CustomerBookings getBookingById(int bookingId) throws BookingNotFoundException;

    // Get all bookings
    List<CustomerBookings> getAllBookings();

    // Remove a booking by its ID
    String removeBooking(int bookingId) throws BookingNotFoundException;
    
    List<CustomerBookings> findByCheckoutDate(Date checkoutDate);

    // Method to find bookings by check-in date
    List<CustomerBookings> findByCheckinDate(Date checkinDate);

    // Method to calculate total revenue
    BigDecimal calculateRevenue();

    // Method to get all bookings by customer name
    List<CustomerBookings> getAllBookingByCustName(String customerName);

    // Method to get booking by customer name
    List<CustomerBookings> getByCustEmail(String customerName);
}
