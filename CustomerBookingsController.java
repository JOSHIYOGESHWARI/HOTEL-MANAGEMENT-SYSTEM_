package com.hotel.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.entity.CustomerBookings;
import com.hotel.exception.BookingNotFoundException;
import com.hotel.service.CustomerBookingServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/custbookings")
public class CustomerBookingsController {

    private final CustomerBookingServiceImpl bookingService;
	
	public CustomerBookingsController(CustomerBookingServiceImpl bookingService) {
		this.bookingService = bookingService;
	}
	
    @PostMapping("/addbooking")
    public ResponseEntity<CustomerBookings> addBooking(@Valid @RequestBody CustomerBookings booking) {
        CustomerBookings newBooking = bookingService.addBooking(booking);
        return new ResponseEntity<>(newBooking, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerBookings> updateBooking(@PathVariable int id, @RequestBody CustomerBookings booking) throws BookingNotFoundException {
        booking.setBookingId(id);
        CustomerBookings updatedBooking = bookingService.updateBooking(booking);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }
    @GetMapping("/getbooking/{id}")
    public ResponseEntity<CustomerBookings> getBookingById(@PathVariable int id) throws BookingNotFoundException {
        CustomerBookings booking = bookingService.getBookingById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
    @GetMapping("/getallbookings")
    public ResponseEntity<List<CustomerBookings>> getAllBookings() {
        List<CustomerBookings> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
    @DeleteMapping("/deletebooking/{id}")
    public ResponseEntity<String> removeBooking(@PathVariable int id) throws BookingNotFoundException {
        String response = bookingService.removeBooking(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/findbycheckout/{checkout}")
    public ResponseEntity<List<CustomerBookings>> findByCheckoutDate(@PathVariable Date checkout) {
        List<CustomerBookings> bookings = bookingService.findByCheckoutDate(checkout);
        return new ResponseEntity<List<CustomerBookings>>(bookings, HttpStatus.OK);
    }

    @GetMapping("/findbycheckin/{checkin}")
    public ResponseEntity<List<CustomerBookings>> findByCheckinDate(@PathVariable Date checkin) {
        List<CustomerBookings> bookings = bookingService.findByCheckinDate(checkin);
        return new ResponseEntity<List<CustomerBookings>>(bookings, HttpStatus.OK);
    }

    @GetMapping("/totalrevenue")
    public ResponseEntity<BigDecimal> calculateRevenue() {
        BigDecimal revenue = bookingService.calculateRevenue();
        return new ResponseEntity<BigDecimal>(revenue, HttpStatus.OK);
    }

    @GetMapping("/allbookingbyname/{customer}")
    public ResponseEntity<List<CustomerBookings>> getAllBookingByCustName(@PathVariable String customer) {
        List<CustomerBookings> bookings = bookingService.getAllBookingByCustName(customer);
        return new ResponseEntity<List<CustomerBookings>>(bookings, HttpStatus.OK);
    }

    @GetMapping("/customer/{email}")
    public ResponseEntity<List<CustomerBookings>> getByCustEmail(@PathVariable String email) {
    	List<CustomerBookings> booking = bookingService.getByCustEmail(email);
        return new ResponseEntity<List<CustomerBookings>>(booking, HttpStatus.OK);
    }
 
}
