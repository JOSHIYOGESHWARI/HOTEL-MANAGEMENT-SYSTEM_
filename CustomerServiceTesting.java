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

import com.hotel.entity.Customer;
import com.hotel.exception.CustomerNotFoundException;
import com.hotel.repository.CustomerRepository;
import com.hotel.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTesting {
     
	Customer customer;
    @Mock
    private CustomerRepository customerRepo;

    @InjectMocks
    private CustomerServiceImpl customerService;

   ;

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
        when(customerRepo.save(any(Customer.class))).thenReturn(customer);
        Customer savedCustomer = customerService.addCustomer(customer);
        assertEquals(customer, savedCustomer);
    }

    @Test
    void testUpdateCustomer() throws CustomerNotFoundException {
        when(customerRepo.findById(anyInt())).thenReturn(Optional.of(customer));
        when(customerRepo.save(any(Customer.class))).thenReturn(customer);
        Customer updatedCustomer = customerService.updateCustomer(1, customer);
        assertEquals(customer, updatedCustomer);
    }

    @Test
    void testFindCustomerById() throws CustomerNotFoundException {
        when(customerRepo.findById(anyInt())).thenReturn(Optional.of(customer));
        Customer foundCustomer = customerService.findCustomerById(1);
        assertEquals(customer, foundCustomer);
    }

    @Test
    void testGetAllCustomers() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepo.findAll()).thenReturn(customers);
        List<Customer> allCustomers = customerService.getAllCustomers();
        assertEquals(customers, allCustomers);
    }

//    @Test
//    void testDeleteCustomer() throws CustomerNotFoundException {
//        customerService.deleteCustomer(1);
//        verify(customerRepo).deleteById(1);
//    }

    @Test
    void testFindByEmail() {
        when(customerRepo.findByEmail(anyString())).thenReturn(customer);
        Customer foundCustomer = customerService.findByEmail("john.doe@example.com");
        assertEquals(customer, foundCustomer);
    }

    @Test
    void testFindByFirstName() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepo.findByFirstName(anyString())).thenReturn(customers);
        List<Customer> foundCustomers = customerService.findByFirstName("John");
        assertEquals(customers, foundCustomers);
    }

    @Test
    void testFindByAddressContaining() {
        List<Customer> customers = Arrays.asList(customer);
        when(customerRepo.findByAddressContaining(anyString())).thenReturn(customers);
        List<Customer> foundCustomers = customerService.findByAddressContaining("City");
        assertEquals(customers, foundCustomers);
    }
}
