package com.hotel.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.entity.CustomerBookings;

@Repository
public interface CustomerBookingsRepository extends JpaRepository<CustomerBookings,Integer> {
	 
    // Find bookings by check-in date
    List<CustomerBookings> findByCheckinDate(Date checkinDate);
 
    // Find bookings by check-out date
    List<CustomerBookings> findByCheckoutDate(Date checkoutDate);
 
    // Get all bookings by customer name
    List<CustomerBookings> findByCustomer_FirstName(String customerName);
 
    // Get a single booking by customer name
    List<CustomerBookings> findByCustomer_Email(String customerName);
    @Query("SELECT SUM(b.totalPrice) FROM CustomerBookings b")
    BigDecimal calculateRevenue();
	
}
