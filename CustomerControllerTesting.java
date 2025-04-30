package com.hotel.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.hotel.controller.CustomerController;
import com.hotel.entity.Customer;
import com.hotel.exception.CustomerNotFoundException;

import com.hotel.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTesting {

	@Mock
    private CustomerServiceImpl customerService;

    @InjectMocks
    private CustomerController customerController;

    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setCustomerId(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setAddress("123 Main Street, City, Country");
        customer.setDob(LocalDate.of(1990, 1, 1));
    }

    @Test
    void testAddCustomer() {
        when(customerService.addCustomer(any(Customer.class))).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.addCustomer(customer);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testUpdateCustomer() throws CustomerNotFoundException {
        when(customerService.updateCustomer(anyInt(), any(Customer.class))).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.updateCustomer(1, customer);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testFindCustomerById() throws CustomerNotFoundException {
        when(customerService.findCustomerById(anyInt())).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.findCustomerById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerService.getAllCustomers()).thenReturn(customers);
        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    void testDeleteCustomer() throws CustomerNotFoundException {
        when(customerService.deleteCustomer(anyInt())).thenReturn("Customer Deleted");
        ResponseEntity<String> response = customerController.deleteCustomer(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer Deleted", response.getBody());
    }


    @Test
    void testFindByEmail() {
        when(customerService.findByEmail(anyString())).thenReturn(customer);
        ResponseEntity<Customer> response = customerController.findByEmail("john.doe@example.com");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customer, response.getBody());
    }

    @Test
    void testFindByFirstName() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerService.findByFirstName(anyString())).thenReturn(customers);
        ResponseEntity<List<Customer>> response = customerController.findByFirstName("John");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }

    @Test
    void testFindByAddressContaining() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerService.findByAddressContaining(anyString())).thenReturn(customers);
        ResponseEntity<List<Customer>> response = customerController.findByAddressContaining("City");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customers, response.getBody());
    }
}
