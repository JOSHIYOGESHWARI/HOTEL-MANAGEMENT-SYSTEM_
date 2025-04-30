package com.hotel.testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hotel.controller.InventoryServiceController;
import com.hotel.dto.InventoryDTO;
import com.hotel.dto.NotificationDTO;
import com.hotel.feignclient.NotificationFeignClient;
import com.hotel.service.InventoryServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)  // Enables Mockito extension for JUnit 5
class InventoryControllerTest {

    @Mock   // creates mock object 
    private InventoryServiceInterface inventoryService;  // Mocked inventory service dependency

    @Mock
    private NotificationFeignClient notificationFeignClient;  // Mocked notification client dependency

    @InjectMocks  // automatically inject mock dependencies into the class 
    private InventoryServiceController inventoryServiceController;

    private InventoryDTO inventoryDTO;

    @BeforeEach   // Runs before each test method
    void setUp() {
        inventoryDTO = new InventoryDTO();  // Creates a new InventoryDTO instance
        inventoryDTO.setItemId(1);  // Sets a unique ID for the item
        inventoryDTO.setItemName("Test Item Name with more than 100 characters to satisfy validation...");
     // Sets a long name to test validation rules
        inventoryDTO.setItemQuantity(50);  // Sets an initial quantity of 50
    }

    @Test    // marks a method as a test case.
    void testAddInventoryItem() {
        when(inventoryService.addInventoryItem(any(InventoryDTO.class))).thenReturn(inventoryDTO);
        
        InventoryDTO response = inventoryServiceController.addInventoryItem(inventoryDTO);
        
        assertNotNull(response); // Check if the response is not null
        assertEquals(inventoryDTO.getItemId(), response.getItemId()); // Verify the item ID
        assertEquals(inventoryDTO.getItemName(), response.getItemName());  // Verify the item name
    }

    @Test
    void testGetAllInventoryItems() {
        List<InventoryDTO> inventoryList = Arrays.asList(inventoryDTO);
        when(inventoryService.getAllInventoryItems()).thenReturn(inventoryList);
        
        List<InventoryDTO> response = inventoryServiceController.getAllInventoryItems();
        
        assertEquals(1, response.size()); // Verify list size
        assertEquals(inventoryDTO.getItemId(), response.get(0).getItemId()); // Verify item ID
    }

    @Test
    void testGetInventoryItem() {
        when(inventoryService.getInventoryItem(anyInt())).thenReturn(inventoryDTO);
        
        InventoryDTO response = inventoryServiceController.getInventoryItem(1);
        
        assertEquals(inventoryDTO.getItemId(), response.getItemId());  // Verify item ID
    }

    @Test
    void testUpdateInventoryItem() {
        when(inventoryService.updateInventoryItem(anyInt(), any(InventoryDTO.class))).thenReturn(inventoryDTO);
        
        InventoryDTO response = inventoryServiceController.updateInventoryItem(1, inventoryDTO);
        
        assertEquals(inventoryDTO.getItemId(), response.getItemId());  // Verify item ID
        assertEquals(inventoryDTO.getItemName(), response.getItemName());  // Verify item name
    }

    @Test
    void testDeleteInventoryItem() {
        when(inventoryService.deleteInventoryItem(anyInt())).thenReturn(true);
        
        boolean response = inventoryServiceController.deleteInventoryItem(1);
        
        assertTrue(response);  // Verify deletion success
    }

    @Test
    void testGetTotalQuantity() {
        when(inventoryService.getTotalQuantity()).thenReturn(500);
        
        int response = inventoryServiceController.getTotalQuantity();
        
        assertEquals(500, response);  // Verify total quantity
    }

    @Test
    void testGetAllNotifications() {
        List<NotificationDTO> notifications = Arrays.asList(new NotificationDTO());
        when(notificationFeignClient.getAllNotifications()).thenReturn(notifications);
        
        List<NotificationDTO> response = inventoryServiceController.getAllNotifications();
        
        assertNotNull(response);  // Verify response is not null
        assertEquals(1, response.size());  // Verify list size
    }
}

