package com.hotel.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.entity.CustomerBookings;
import com.hotel.exception.BookingNotFoundException;
import com.hotel.repository.CustomerBookingsRepository;

@Service
public class CustomerBookingServiceImpl implements CustomerBookingsServiceInterface {
	 
	
	private final CustomerBookingsRepository customerbookingrepo;
	
	public CustomerBookingServiceImpl(CustomerBookingsRepository customerbookingrepo) {
		this.customerbookingrepo = customerbookingrepo;
	}

	
	@Override
	public CustomerBookings addBooking(CustomerBookings booking) {
		// TODO Auto-generated method stub
		return customerbookingrepo.save(booking);
	}

	@Override
	public CustomerBookings updateBooking(CustomerBookings booking)throws BookingNotFoundException {
		// TODO Auto-generated method stub
		CustomerBookings cb = customerbookingrepo.findById(booking.getBookingId()).orElseThrow(()-> new BookingNotFoundException("Booking Not Found"));		
		
		cb.setCheckinDate(booking.getCheckinDate());
		cb.setCheckoutDate(booking.getCheckoutDate());
		cb.setRoomId(booking.getRoomId());
		cb.setStatus(booking.isStatus());
		cb.setTotalPrice(booking.getTotalPrice());
		return customerbookingrepo.save(booking);
	}

	@Override
	public CustomerBookings getBookingById(int bookingId) throws BookingNotFoundException{
		// TODO Auto-generated method stub
		CustomerBookings booking = customerbookingrepo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking Not Found"));		
        return booking;
	}

	@Override
	public List<CustomerBookings> getAllBookings() {
		// TODO Auto-generated method stub
		 return customerbookingrepo.findAll();
	}

	@Override
	public String removeBooking(int bookingId) throws BookingNotFoundException{
		// TODO Auto-generated method stub
		CustomerBookings booking = customerbookingrepo.findById(bookingId).orElseThrow(()-> new BookingNotFoundException("Booking Not Found"));		
		customerbookingrepo.deleteById(bookingId);
		return "Booking deleted";
	}

	@Override
	public List<CustomerBookings> findByCheckoutDate(Date checkoutDate) {
		// TODO Auto-generated method stub
		return customerbookingrepo.findByCheckoutDate(checkoutDate);
	}

	@Override
	public List<CustomerBookings> findByCheckinDate(Date checkinDate) {
		// TODO Auto-generated method stub
		return customerbookingrepo.findByCheckinDate(checkinDate);
	}

	@Override
	public BigDecimal calculateRevenue() {
		// TODO Auto-generated method stub
		return customerbookingrepo.calculateRevenue();
	}

	@Override
	public List<CustomerBookings> getAllBookingByCustName(String customerName) {
		// TODO Auto-generated method stub
		return customerbookingrepo.findByCustomer_FirstName(customerName);
	}

	@Override
	public List<CustomerBookings> getByCustEmail(String customerName) {
		// TODO Auto-generated method stub
		return customerbookingrepo.findByCustomer_Email(customerName);
	}

}
