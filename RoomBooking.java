package com.hotel.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The RoomBooking class represents a booking of a room in the hotel. It
 * contains details about the booking such as check-in and check-out dates,
 * booking price, room status, and the associated customer and room.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "room_booking") 
public class RoomBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generate unique booking ID
	@Column(name = "booking_id")
	private int bookingId; // Unique identifier for each booking

	@Column(name = "status")
	@NotNull(message = "Status cannot be null") 
	private boolean status; // Status of the booking 

	@Column(name = "check_in_date")
	@JsonFormat(pattern = "yyyy-MM-dd") // Specifies the date format for JSON serialization
	@FutureOrPresent(message = "Check in date must be today or in future") 
	@NotNull(message = "Check in date cannot be null") 
	private LocalDate checkInDate; // The date the customer checks into the room

	@Column(name = "check_out_date")
	@JsonFormat(pattern = "yyyy-MM-dd") // Specifies the date format for JSON serialization
	@FutureOrPresent(message = "Check out date must be today or in future") 
	@NotNull(message = "Check out date cannot be null") 
	private LocalDate checkOutDate; // The date the customer checks out of the room

	@Column(name = "booking_price")
	@Min(value =500, message = "Booking price must be greater than 500") 
	private double price; // The total price of the booking

	private int customerId; // The ID of the customer making the booking

    @JsonIgnore
	@OneToOne
	@JoinColumn(name = "room_id") // Establishes a one-to-one relationship between booking and room
	private Rooms rooms; // The room that is booked for this booking
    
 // Custom toString() method to avoid infinite recursion
    @Override
    public String toString() {
        return "RoomBooking{" +
                "bookingId=" + bookingId +
                ", status=" + status +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", price=" + price +
                ", customerId=" + customerId +
                ", roomId=" + (rooms != null ? rooms.getRoomId() : null) + // Print only the roomId
                '}';
    }
}
