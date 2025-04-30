package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.entity.RoomType;

/**
 * Repository interface for managing RoomType entities.
 * Provides methods to interact with the RoomType data in the database.
 */
@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {

    // No custom methods are added yet, as JpaRepository provides standard CRUD operations
}
