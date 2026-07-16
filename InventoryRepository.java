package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hotel.entity.Inventory;

//Repository interface to interact with the Inventory entity in the database
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
	
	
    
}
