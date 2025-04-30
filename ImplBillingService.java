package com.billing.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.billing.client.CustomerClient;  // Feign client for accessing customer details
import com.billing.client.RoomBookingClient; // Feign client for accessing room booking details
import com.billing.entity.Billing; 
import com.billing.exception.BillingNotFoundException;
import com.billing.exception.CustomerNotFoundException;
import com.billing.exception.RoomBookingNotFoundException;
import com.billing.repository.BillingRepository;
import com.billing.dto.Customer;  // DTO representing customer details
import com.billing.dto.RoomBooking;  // DTO representing room booking details

/**
 * Implementation of the billing service interface, handling business logic for billing operations.
 */
@Service
public class ImplBillingService implements IntBillingService {

    private final BillingRepository billingRepository;
    private final RoomBookingClient roomBookingClient; 
    private final CustomerClient customerClient;

    /**
     * Constructor injection for dependencies.
     *
     * @param billingRepository the billing repository to interact with the database
     * @param roomBookingClient the room booking client to interact with the room booking service
     * @param customerClient the customer client to interact with the customer service
     */

    public ImplBillingService(BillingRepository billingRepository, RoomBookingClient roomBookingClient, CustomerClient customerClient) {
        this.billingRepository = billingRepository;
        this.roomBookingClient = roomBookingClient; 
        this.customerClient = customerClient;
    }

    /**
     * Adds a new billing record to the repository.
     *
     * @param billing the billing details to add
     * @return the saved billing record
     */
    @Override
    public Billing addBill(Billing billing) {
        return billingRepository.save(billing);
    }

    /**
     * Retrieves all billing records.
     *
     * @return a list of all billing records
     */
    @Override
    public List<Billing> getAllBillingItems() {
        return billingRepository.findAll();
    }

    /**
     * Retrieves a billing record by its ID.
     *
     * @param id the ID of the billing record to retrieve
     * @return the billing record
     * @throws BillingNotFoundException if the billing record is not found
     */
    @Override
    public Billing getBillById(int id) throws BillingNotFoundException {
        return billingRepository.findById(id)
                .orElseThrow(() -> new BillingNotFoundException("Billing not found for id: " + id));
    }

    /**
     * Retrieves room booking details by booking ID.
     *
     * @param bookingId the ID of the room booking
     * @return the room booking details wrapped in a ResponseEntity
     * @throws RoomBookingNotFoundException if the room booking is not found
     */
    @Override
    public ResponseEntity<RoomBooking> getBookingById(int bookingId) throws RoomBookingNotFoundException {
        return roomBookingClient.getBookingById(bookingId);  
    }

    /**
     * Retrieves customer details by customer ID.
     *
     * @param customerId the ID of the customer
     * @return the customer details wrapped in a ResponseEntity
     * @throws CustomerNotFoundException if the customer is not found
     */
    @Override
    public ResponseEntity<Customer> findCustomerById(int customerId) throws CustomerNotFoundException {
        return customerClient.findCustomerById(customerId);
    }
}
