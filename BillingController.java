package com.billing.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.billing.dto.Customer;
import com.billing.dto.RoomBooking;
import com.billing.entity.Billing;
import com.billing.exception.CustomerNotFoundException;
import com.billing.exception.RoomBookingNotFoundException;
import com.billing.service.IntBillingService;

import jakarta.validation.Valid;

/**
 * REST controller for managing billing operations.
 * This controller exposes endpoints for adding new billing records, retrieving billing records, and fetching all billing items.
 */
@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private IntBillingService billingService;  // Service for billing operations

    /**
     * Retrieves all billing items.
     *
     * @return a list of all billing items
     */
    @GetMapping("/all")
    public List<Billing> getAllBillingItems() {
        // Fetch and return all billing items from the service
        return billingService.getAllBillingItems();
    }

    /**
     * Adds a new billing record.
     * This method handles creating a billing record by fetching customer and room booking details, 
     * calculating the total amount including GST, and saving the billing record.
     *
     * @param billing the billing details to add
     * @return the added billing record
     * @throws CustomerNotFoundException if the customer is not found
     * @throws RoomBookingNotFoundException if the room booking is not found
     */
    @PostMapping("/add")
    public Billing addBill(@Valid @RequestBody Billing billing) throws CustomerNotFoundException, RoomBookingNotFoundException {
        // Fetch customer details from the service using customer ID
        ResponseEntity<Customer> customer = billingService.findCustomerById(billing.getCustomerId());
        billing.setCustomerName(customer.getBody().getFirstName());

        // Fetch room booking details from the service using booking ID
        ResponseEntity<RoomBooking> room = billingService.getBookingById(billing.getBookingId());
        billing.setRoomPrice(room.getBody().getPrice());

        // Calculate GST (18%)
        double gstAmount = billing.getRoomPrice() * 0.18;
        double totalAmount = billing.getRoomPrice() + gstAmount;

        // Set final values for the billing
        billing.setAmount(totalAmount);  // Set total amount including GST
        billing.setStatus("Pending");  // Default status is "Pending"
        billing.setBillingDate(LocalDateTime.now());  // Set the current date and time

        // Save and return the newly added billing record
        return billingService.addBill(billing);
    }

    /**
     * Retrieves a billing record by its ID.
     *
     * @param id the ID of the billing record to retrieve
     * @return the billing record corresponding to the provided ID
     */
    @GetMapping("/byid/{id}")
    public Billing getBillById(@PathVariable int id) {
        // Fetch and return the billing record by its ID
        return billingService.getBillById(id);
    }
}
