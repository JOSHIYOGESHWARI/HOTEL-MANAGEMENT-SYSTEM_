package com.hotel.service;

import com.hotel.dto.InventoryDTO;
import com.hotel.entity.Inventory;
import com.hotel.exception.InventoryNotFoundException;
import com.hotel.feignclient.NotificationFeignClient;
import com.hotel.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// Service implementation for Inventory-related operations
@Service
public class InventoryServiceImpl implements InventoryServiceInterface {

   
    public final  InventoryRepository inventoryRepository; // Injecting the InventoryRepository
    
    public final NotificationFeignClient notificationFeignClient;
    
    public InventoryServiceImpl (InventoryRepository inventoryRepository, NotificationFeignClient notificationFeignClient) {
    	this.inventoryRepository =inventoryRepository;
    	this.notificationFeignClient = notificationFeignClient;
    	
    }

    // Method to add a new inventory item
    @Override
    public InventoryDTO addInventoryItem(InventoryDTO inventoryDTO) {
        Inventory inventory = new Inventory();
        inventory.setItemName(inventoryDTO.getItemName());
        inventory.setItemQuantity(inventoryDTO.getItemQuantity());
        inventory.setTimestamp(LocalDateTime.now()); // Setting current timestamp
        Inventory savedInventory = inventoryRepository.save(inventory); // Saving the inventory item
        return convertToDTO(savedInventory); // Converting to DTO and returning
    }

    // Method to get a list of all inventory items
    @Override
    public List<InventoryDTO> getAllInventoryItems() {
        return inventoryRepository.findAll().stream()
                .map(this::convertToDTO) // Converting each inventory entity to DTO
                .collect(Collectors.toList()); // Collecting the results into a list
    }

    // Method to get a specific inventory item by its ID
    @Override
    public InventoryDTO getInventoryItem(int itemId) throws InventoryNotFoundException {
        Inventory inventory = inventoryRepository.findById(itemId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory item not found")); // Throw exception if not found
        return convertToDTO(inventory); // Converting to DTO and returning
    }
    
    

    // Method to update an inventory item by its ID
    @Override
    public InventoryDTO updateInventoryItem(int itemId, InventoryDTO inventoryDTO) throws InventoryNotFoundException {
        Inventory inventory = inventoryRepository.findById(itemId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory item not found")); // Throw exception if not found
        inventory.setItemName(inventoryDTO.getItemName());
        inventory.setItemQuantity(inventoryDTO.getItemQuantity());
        inventory.setTimestamp(LocalDateTime.now()); // Setting new timestamp
        Inventory updatedInventory = inventoryRepository.save(inventory); // Saving updated inventory item
        return convertToDTO(updatedInventory); // Converting to DTO and returning
    }

    // Method to delete an inventory item by its ID
    @Override
    public boolean deleteInventoryItem(int itemId) {
        if (inventoryRepository.existsById(itemId)) {
            inventoryRepository.deleteById(itemId); // Deleting the inventory item
            return true;
        }
        throw new InventoryNotFoundException("Inventory item not found"); // Throw exception if not found
    }

    // Method to get the total quantity of all inventory items
    @Override
    public int getTotalQuantity() {
        return inventoryRepository.findAll().stream()
                .mapToInt(Inventory::getItemQuantity) // Summing the quantities of all items
                .sum();
    }

    // Helper method to convert Inventory entity to DTO
    private InventoryDTO convertToDTO(Inventory inventory) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setItemId(inventory.getItemId());
        inventoryDTO.setItemName(inventory.getItemName());
        inventoryDTO.setItemQuantity(inventory.getItemQuantity());
        inventoryDTO.setTimestamp(inventory.getTimestamp());
        return inventoryDTO;
    }

	

	
}
