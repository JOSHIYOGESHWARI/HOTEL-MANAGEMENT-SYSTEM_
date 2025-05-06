package com.hotel.controller;

import com.hotel.dto.NotificationDTO;
import com.hotel.feignclient.InventoryFeignClient;
import com.hotel.service.NotificationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationServiceController {

    @Autowired
    private NotificationServiceInterface notificationService;

    @Autowired
    private InventoryFeignClient inventoryFeignClient;

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<NotificationDTO> notifications = notificationService.getAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable int id) {
        NotificationDTO notification = notificationService.getNotificationById(id);
        if (notification != null) {
            return new ResponseEntity<>(notification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<NotificationDTO> updateNotificationStatus(@PathVariable int id, @RequestParam String status) {
        NotificationDTO updatedNotification = notificationService.updateNotificationStatus(id, status);
        if (updatedNotification != null) {
            return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(@PathVariable int id) {
        boolean isDeleted = notificationService.deleteNotification(id);
        if (isDeleted) {
            return new ResponseEntity<>("Notification deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Notification not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }
}
