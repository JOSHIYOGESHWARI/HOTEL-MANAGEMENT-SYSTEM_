package com.hotel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hotel.dto.Rooms;
import com.hotel.entity.Customer;
import com.hotel.exception.CustomerNotFoundException;
import com.hotel.exception.RoomNotFoundException;
import com.hotel.feignclient.RoomFeignClient;
import com.hotel.repository.CustomerRepository;

import jakarta.validation.Valid;
import lombok.var;

@Service
public class CustomerServiceImpl implements CustomerServiceInterface{
	
	private final CustomerRepository customerRepo;

	private final RoomFeignClient roomclient;

	
	public CustomerServiceImpl(CustomerRepository customerRepo,RoomFeignClient roomclient) {
		this.customerRepo = customerRepo;
		this.roomclient = roomclient;
	}
	
	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(int customerId, Customer customerDetails)throws CustomerNotFoundException{
		// TODO Auto-generated method stub
		Customer c = customerRepo.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer not Found"));
		
		c.setFirstName(customerDetails.getFirstName());
		c.setLastName(customerDetails.getLastName());
		c.setAddress(customerDetails.getAddress());
		c.setDob(customerDetails.getDob());
		c.setEmail(customerDetails.getEmail());
		
		return customerRepo.save(c);
	}

	@Override
	public Customer findCustomerById(int customerId)throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return customerRepo.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer not Found"));
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepo.findAll();
	}

	@Override
	public String deleteCustomer(int customerId)throws CustomerNotFoundException{
		// TODO Auto-generated method stub
		Customer c = customerRepo.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer not Found"));
	    customerRepo.deleteById(customerId);
	    return "Customer Deleted";
	}

    @Override
    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return customerRepo.findByFirstName(firstName);
    }

    @Override
    public List<Customer> findByAddressContaining(String city) {
        return customerRepo.findByAddressContaining(city);
    }

	@Override
	public ResponseEntity<Rooms> getRoomByRoomId(int roomId) throws RoomNotFoundException {
		// TODO Auto-generated method stub
		return roomclient.getRoomByRoomId(roomId);
	}

    
    // From Feign Client.
//	@Override
//	public Rooms getRoomById(int roomId) {
//		// TODO Auto-generated method stub
//		return roomclient.getRoomById(roomId);
//	}
}
