package com.billing.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.billing.dto.Customer;
import com.billing.dto.RoomBooking;
import com.billing.entity.Billing;
import com.billing.exception.CustomerNotFoundException;
import com.billing.exception.RoomBookingNotFoundException;

/**
 * Interface for the Billing service that provides methods for managing billing operations.
 * The service includes operations for adding bills, retrieving billing items, 
 * and fetching room bookings and customer details.
 */
public interface IntBillingService {

    /**
     * Adds a new billing record.
     *
     * @param billing the billing record to be added
     * @return the added billing record
     * @throws CustomerNotFoundException if the customer is not found
     * @throws RoomBookingNotFoundException if the room booking is not found
     */
    Billing addBill(Billing billing) throws CustomerNotFoundException, RoomBookingNotFoundException;

    /**
     * Retrieves all billing records.
     *
     * @return a list of all billing records
     */
    List<Billing> getAllBillingItems();

    /**
     * Retrieves a billing record by its ID.
     *
     * @param id the ID of the billing record to retrieve
     * @return the billing record
     */
    Billing getBillById(int id);

    /**
     * Retrieves room booking details by booking ID.
     *
     * @param bookingId the ID of the room booking
     * @return the room booking details wrapped in a ResponseEntity
     * @throws RoomBookingNotFoundException if the room booking is not found
     */
    ResponseEntity<RoomBooking> getBookingById(int bookingId) throws RoomBookingNotFoundException;

    /**
     * Retrieves customer details by customer ID.
     *
     * @param customerId the ID of the customer
     * @return the customer details wrapped in a ResponseEntity
     * @throws CustomerNotFoundException if the customer is not found
     */
    ResponseEntity<Customer> findCustomerById(int customerId) throws CustomerNotFoundException;
}
