package com.hotel.entity;

import java.math.BigDecimal;
import java.sql.Date;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="customer_bookings")
public class CustomerBookings {

	// Unique id for the booking
	@Id
	@Column(name="booking_id")
	private int bookingId;
	
	// Reference to the customer who made the booking
	@ManyToOne
    @JoinColumn(name="customer_id")
	@JsonBackReference
	private Customer customer; 
	
	// Identifier for the room being booked
	@Column(name="room_id")
	@NotNull(message="Please enter room id")
	@Positive(message="Please enter room id")
	private int roomId;
	
	// Check-in date for the booking
	@Column(name="check_in_date")
	@FutureOrPresent(message="Please enter valid checkin date")
	private Date checkinDate;
	
	// Check-out date for the booking
	@Column(name="check_out_date")
	@FutureOrPresent(message="Please enter valid checkout date")
	private Date checkoutDate;
	
	// Total price for the booking
	@Column(name="total_price")
	@Positive(message="Total price should be greater than zero")
	private double totalPrice;
	
	// Status of the booking
	@Column(name="status")
	@AssertTrue(message="Status should be true")
	private boolean status;
	

}
