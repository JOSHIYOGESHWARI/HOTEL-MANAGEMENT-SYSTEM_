package com.billing.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for Room Booking details.
 * This DTO is used to transfer the room booking data between different layers in the application.
 */
@Data
@NoArgsConstructor  // No-argument constructor for RoomBooking
@AllArgsConstructor // Constructor with arguments for RoomBooking
public class RoomBooking {

    /**
     * The unique identifier for the booking.
     */
    private int bookingId;

    /**
     * The status of the room booking (active/inactive).
     */
    private Boolean status;

    /**
     * The check-in date for the room booking.
     * The date format is yyyy-MM-dd.
     */
    @JsonFormat(pattern = "yyyy-MM-dd") // Specifies the date format during JSON serialization/deserialization
    private LocalDate checkInDate;

    /**
     * The check-out date for the room booking.
     * The date format is yyyy-MM-dd.
     */
    @JsonFormat(pattern = "yyyy-MM-dd") // Specifies the date format during JSON serialization/deserialization
    private LocalDate checkOutDate;

    /**
     * The price of the room for the booking.
     */
    private double price;

    /**
     * The unique identifier for the customer who made the booking.
     */
    private int customerId;

    /**
     * The unique identifier for the room that was booked.
     */
    private int roomId;
}
