package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{


	Customer findByEmail(String email);

	List<Customer> findByFirstName(String firstName);

	List<Customer> findByAddressContaining(String city);
}
