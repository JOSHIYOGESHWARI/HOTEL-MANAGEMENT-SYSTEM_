package com.billing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.billing.dto.RoomBooking;

/**
 * Feign client interface for accessing the Room Booking microservice.
 * This client is used to communicate with the Room Booking service and fetch room booking details.
 */
@FeignClient(name = "ROOMMICROSERVICE", url = "http://localhost:8002")
public interface RoomBookingClient {

    /**
     * Retrieves a room booking by its ID.
     * This method sends a GET request to the Room Booking microservice to fetch a room booking 
     * based on the provided booking ID.
     *
     * @param bookingId the ID of the room booking to retrieve
     * @return the room booking details wrapped in a ResponseEntity
     */
    @GetMapping("/roombookings/id/{bookingId}")
    ResponseEntity<RoomBooking> getBookingById(@PathVariable int bookingId);
}
