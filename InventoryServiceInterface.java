package com.hotel.service;

import com.hotel.dto.InventoryDTO;
import com.hotel.exception.InventoryNotFoundException;

import java.util.List;

public interface InventoryServiceInterface {

    InventoryDTO addInventoryItem(InventoryDTO inventoryDTO);

    List<InventoryDTO> getAllInventoryItems();

    InventoryDTO getInventoryItem(int itemId);

    InventoryDTO updateInventoryItem(int itemId, InventoryDTO inventoryDTO);

    boolean deleteInventoryItem(int itemId);

    int getTotalQuantity();

	

	
}
