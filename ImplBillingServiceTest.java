package com.billing.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.billing.client.CustomerClient;
import com.billing.client.RoomBookingClient;
import com.billing.dto.Customer;
import com.billing.dto.RoomBooking;
import com.billing.entity.Billing;
import com.billing.exception.BillingNotFoundException;
import com.billing.exception.CustomerNotFoundException;
import com.billing.exception.RoomBookingNotFoundException;
import com.billing.repository.BillingRepository;
import com.billing.service.ImplBillingService;

/**
 * Unit tests for the ImplBillingService class.
 * This class contains tests for adding bills, retrieving all billing items,
 * retrieving a billing record by its ID, and interacting with Feign clients.
 */
@ExtendWith(MockitoExtension.class)  // Extends with MockitoExtension to support Mockito annotations
class ImplBillingServiceTest {

    @Mock
    private BillingRepository billingRepository;  // Mocking the BillingRepository dependency

    @Mock
    private RoomBookingClient roomclient;  // Mocking the RoomBookingClient dependency

    @Mock
    private CustomerClient custclient;  // Mocking the CustomerClient dependency

    @InjectMocks
    private ImplBillingService billingService;  // Injecting the mocks into ImplBillingService

    private Billing billing;  // Instance variable for the Billing object used in tests

    /**
     * Setup method to initialize the Billing object before each test.
     */
    @BeforeEach
    void setUp() {
        // Initializing the billing object with sample data for testing
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
     * This test mocks the BillingRepository to simulate saving a billing record.
     */
    @Test
    void testAddBill() {
        // Mocking the save method of the BillingRepository
        when(billingRepository.save(any(Billing.class))).thenReturn(billing);

        // Call the service method and assert the result
        Billing createdBilling = billingService.addBill(billing);
        assertEquals(billing, createdBilling);
    }

    /**
     * Test for retrieving all billing items.
     * This test mocks the BillingRepository to simulate fetching all billing records.
     */
    @Test
    void testGetAllBillingItems() {
        List<Billing> billings = Arrays.asList(billing);  // Mocked list of billings
        when(billingRepository.findAll()).thenReturn(billings);  // Mocking the findAll method

        // Call the service method and assert the result
        List<Billing> result = billingService.getAllBillingItems();
        assertEquals(billings, result);
    }

    /**
     * Test for retrieving a billing record by its ID.
     * This test mocks the BillingRepository to simulate fetching a billing record by ID.
     * 
     * @throws BillingNotFoundException if billing record not found
     */
    @Test
    void testGetBillById() throws BillingNotFoundException {
        // Mocking the findById method of the BillingRepository
        when(billingRepository.findById(anyInt())).thenReturn(Optional.of(billing));

        // Call the service method and assert the result
        Billing result = billingService.getBillById(1);
        assertEquals(billing, result);
    }

    /**
     * Test for retrieving a billing record by its ID when the billing is not found.
     * This test simulates the case when the billing record is not found in the repository.
     */
    @Test
    void testGetBillById_NotFound() {
        // Mocking the findById method to return empty, simulating no record found
        when(billingRepository.findById(anyInt())).thenReturn(Optional.empty());

        // Assert that BillingNotFoundException is thrown
        assertThrows(BillingNotFoundException.class, () -> {
            billingService.getBillById(1);
        });
    }

    /**
     * Test for retrieving a room booking by its ID.
     * This test mocks the RoomBookingClient to simulate fetching a room booking.
     * 
     * @throws RoomBookingNotFoundException if room booking is not found
     */
    @Test
    void testGetBookingById() throws RoomBookingNotFoundException {
        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setPrice(1000);

        // Mocking the RoomBookingClient to return a mocked RoomBooking
        when(roomclient.getBookingById(anyInt())).thenReturn(new ResponseEntity<>(roomBooking, HttpStatus.OK));

        // Call the service method and assert the result
        ResponseEntity<RoomBooking> response = billingService.getBookingById(1);
        assertEquals(roomBooking, response.getBody());
    }

    /**
     * Test for finding a customer by their ID.
     * This test mocks the CustomerClient to simulate fetching a customer.
     * 
     * @throws CustomerNotFoundException if customer is not found
     */
    @Test
    void testFindCustomerById() throws CustomerNotFoundException {
        Customer customer = new Customer();
        customer.setFirstName("John");

        // Mocking the CustomerClient to return a mocked Customer
        when(custclient.findCustomerById(anyInt())).thenReturn(new ResponseEntity<>(customer, HttpStatus.OK));

        // Call the service method and assert the result
        ResponseEntity<Customer> response = billingService.findCustomerById(1);
        assertEquals(customer, response.getBody());
    }
}
