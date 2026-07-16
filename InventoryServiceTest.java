package com.hotel.testing;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hotel.dto.InventoryDTO;
import com.hotel.entity.Inventory;
import com.hotel.exception.InventoryNotFoundException;
import com.hotel.feignclient.NotificationFeignClient;
import com.hotel.repository.InventoryRepository;
import com.hotel.service.InventoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private NotificationFeignClient notificationFeignClient;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    private Inventory inventory;
    private InventoryDTO inventoryDTO;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        inventory.setItemId(1);
        inventory.setItemName("Test Item Name with more than 100 characters to satisfy validation...");
        inventory.setItemQuantity(50);
        inventory.setTimestamp(LocalDateTime.now());

        inventoryDTO = new InventoryDTO();
        inventoryDTO.setItemId(1);
        inventoryDTO.setItemName(inventory.getItemName());
        inventoryDTO.setItemQuantity(inventory.getItemQuantity());
        inventoryDTO.setTimestamp(inventory.getTimestamp());
    }

    @Test
    void testAddInventoryItem() {
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        InventoryDTO response = inventoryService.addInventoryItem(inventoryDTO);

        assertNotNull(response);
        assertEquals(inventory.getItemId(), response.getItemId());
        assertEquals(inventory.getItemName(), response.getItemName());
        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }

    @Test
    void testGetAllInventoryItems() {
        when(inventoryRepository.findAll()).thenReturn(Arrays.asList(inventory));

        List<InventoryDTO> response = inventoryService.getAllInventoryItems();

        assertEquals(1, response.size());
        assertEquals(inventory.getItemId(), response.get(0).getItemId());
        verify(inventoryRepository, times(1)).findAll();
    }

    @Test
    void testGetInventoryItemFound() {
        when(inventoryRepository.findById(1)).thenReturn(Optional.of(inventory));

        InventoryDTO response = inventoryService.getInventoryItem(1);

        assertNotNull(response);
        assertEquals(inventory.getItemId(), response.getItemId());
        verify(inventoryRepository, times(1)).findById(1);
    }

    @Test
    void testGetInventoryItemNotFound() {
        when(inventoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InventoryNotFoundException.class, () -> inventoryService.getInventoryItem(1));
        verify(inventoryRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateInventoryItemFound() {
        when(inventoryRepository.findById(1)).thenReturn(Optional.of(inventory));
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        InventoryDTO response = inventoryService.updateInventoryItem(1, inventoryDTO);

        assertNotNull(response);
        assertEquals(inventoryDTO.getItemId(), response.getItemId());
        assertEquals(inventoryDTO.getItemName(), response.getItemName());
        verify(inventoryRepository, times(1)).findById(1);
        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }

    @Test
    void testUpdateInventoryItemNotFound() {
        when(inventoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InventoryNotFoundException.class, () -> inventoryService.updateInventoryItem(1, inventoryDTO));
        verify(inventoryRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteInventoryItemFound() {
        when(inventoryRepository.existsById(1)).thenReturn(true);

        boolean result = inventoryService.deleteInventoryItem(1);

        assertTrue(result);
        verify(inventoryRepository, times(1)).existsById(1);
        verify(inventoryRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteInventoryItemNotFound() {
        when(inventoryRepository.existsById(1)).thenReturn(false);

        assertThrows(InventoryNotFoundException.class, () -> inventoryService.deleteInventoryItem(1));
        verify(inventoryRepository, times(1)).existsById(1);
    }

    @Test
    void testGetTotalQuantity() {
        when(inventoryRepository.findAll()).thenReturn(Arrays.asList(inventory));

        int totalQuantity = inventoryService.getTotalQuantity();

        assertEquals(50, totalQuantity);
        verify(inventoryRepository, times(1)).findAll();
    }
}

