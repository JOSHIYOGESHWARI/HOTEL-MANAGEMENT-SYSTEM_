package com.billing.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.billing.controller.BillingController;
import com.billing.dto.Customer;
import com.billing.dto.RoomBooking;
import com.billing.entity.Billing;
import com.billing.exception.CustomerNotFoundException;
import com.billing.exception.RoomBookingNotFoundException;
import com.billing.service.IntBillingService;

/**
 * Unit tests for the BillingController class.
 * This class contains tests for adding bills, retrieving all billing items,
 * and retrieving a billing record by its ID.
 */
@ExtendWith(MockitoExtension.class)  // Extends with MockitoExtension to support Mockito annotations
class BillingControllerTest {

    @Mock
    private IntBillingService billingService;  // Mocking the BillingService dependency

    @InjectMocks
    private BillingController billingController;  // Injecting the mocks into the BillingController

    private Billing billing;  // Instance variable for the Billing object used in tests

    /**
     * Setup method to initialize the Billing object before each test.
     */
    @BeforeEach
    void setUp() {
        billing = new Billing();
        billing.setBillingId(1);
        billing.setCustomerId(1);
        billing.setBookingId(1);
        billing.setRoomPrice(1000);
        billing.setMethod("Credit Card");
        billing.setBillingDate(LocalDateTime.now());
        billing.setStatus("Pending");
    }

    /**
     * Test for adding a new bill.
     * This test mocks the service methods to simulate adding a billing record.
     * 
     * @throws CustomerNotFoundException if customer not found
     * @throws RoomBookingNotFoundException if room booking not found
     */
    @Test
    void testAddBill() throws CustomerNotFoundException, RoomBookingNotFoundException {
        Customer customer = new Customer();
        customer.setFirstName("John");

        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setPrice(1000);

        // Mocking service methods
        when(billingService.findCustomerById(anyInt())).thenReturn(new ResponseEntity<>(customer, HttpStatus.OK));
        when(billingService.getBookingById(anyInt())).thenReturn(new ResponseEntity<>(roomBooking, HttpStatus.OK));
        when(billingService.addBill(any(Billing.class))).thenReturn(billing);

        // Call the controller method and assert the result
        Billing createdBilling = billingController.addBill(billing);
        assertEquals(billing, createdBilling);
    }

    /**
     * Test for retrieving all billing records.
     * This test mocks the service method to simulate retrieving all billing items.
     */
    @Test
    void testGetAllBillingItems() {
        List<Billing> billings = Arrays.asList(billing);  // Mocked list of billings
        when(billingService.getAllBillingItems()).thenReturn(billings);  // Mocking service call

        // Call the controller method and assert the result
        List<Billing> result = billingController.getAllBillingItems();
        assertEquals(billings, result);
    }

    /**
     * Test for retrieving a billing record by its ID.
     * This test mocks the service method to simulate retrieving a billing record by ID.
     * 
     * @throws Exception if an error occurs while fetching the bill
     */
    @Test
    void testGetBillById() throws Exception {
        when(billingService.getBillById(anyInt())).thenReturn(billing);  // Mocking service call

        // Call the controller method and assert the result
        Billing result = billingController.getBillById(1);
        assertEquals(billing, result);
    }
}
