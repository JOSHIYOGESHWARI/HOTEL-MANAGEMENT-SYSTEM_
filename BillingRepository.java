package com.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.billing.entity.Billing;

/**
 * Repository interface for handling operations related to the Billing entity.
 * This interface extends JpaRepository to leverage Spring Data JPA functionalities 
 * for CRUD operations on the Billing entity.
 */
@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {

    // No custom methods needed here yet, but can be added as required.
}
