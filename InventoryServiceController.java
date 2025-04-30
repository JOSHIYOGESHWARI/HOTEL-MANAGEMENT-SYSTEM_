package com.hotel.controller;

import com.hotel.dto.InventoryDTO;
import com.hotel.dto.NotificationDTO;
import com.hotel.feignclient.NotificationFeignClient;
import com.hotel.service.InventoryServiceInterface;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
//Define base URL path for inventory-related operations
@RequestMapping("/inventory")
public class InventoryServiceController {

	// Service for inventory operations
    
    private final InventoryServiceInterface inventoryService;

    // Feign client to interact with the Notification service
//    @Autowired
    private final NotificationFeignClient notificationFeignClient;

    // injecting the InventoryServiceInterface dependency 
    public InventoryServiceController(InventoryServiceInterface inventoryService ,NotificationFeignClient notificationFeignClient) {
    	this.inventoryService = inventoryService ;
    	this.notificationFeignClient = notificationFeignClient ;
    }
    
    
    
    // Endpoint to add a new inventory item
    @PostMapping("/add")
    public InventoryDTO addInventoryItem(@Valid @RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addInventoryItem(inventoryDTO);
    }

    // Endpoint to get all inventory items
    @GetMapping
    public List<InventoryDTO> getAllInventoryItems() {
        return inventoryService.getAllInventoryItems();
    }

    // Endpoint to get an inventory item by its ID
    @GetMapping("id/{id}")
    public InventoryDTO getInventoryItem(@PathVariable int id) {
        return inventoryService.getInventoryItem(id);
    }
    

    // Endpoint to update an inventory item
    @PutMapping("update/{id}")
    public InventoryDTO updateInventoryItem(@PathVariable int id, @RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateInventoryItem(id, inventoryDTO);
    }

    // Endpoint to delete an inventory item by ID
    @DeleteMapping("delete/{id}")
    public boolean deleteInventoryItem(@PathVariable int id) {
        return inventoryService.deleteInventoryItem(id);
    }

    // Endpoint to get the total quantity of all inventory items
    @GetMapping("/totalQuantity")
    public int getTotalQuantity() {
        return inventoryService.getTotalQuantity();
    }

    // Endpoint to get all notifications related to inventory items
    @GetMapping("/notifications")
    public List<NotificationDTO> getAllNotifications() {
        return notificationFeignClient.getAllNotifications();
    }
}
