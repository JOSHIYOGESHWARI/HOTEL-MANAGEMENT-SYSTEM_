package com.hotel.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The CustomerBookings class is a Data Transfer Object (DTO) that represents a
 * booking made by a customer. This object contains all relevant details about
 * the booking and the associated customer.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Ensures that null values are not included in the JSON response
public class CustomerBookings {

	private int bookingId; // Unique identifier for the booking

	private Customer customer; // The customer associated with this booking

	private int roomId; // ID of the room that is booked

	private Date checkinDate; // The check-in date for the booking

	private Date checkoutDate; // The check-out date for the booking

	private double totalPrice; // The total price for the booking

	private boolean status; // The status of the booking (e.g., confirmed, cancelled)
}
