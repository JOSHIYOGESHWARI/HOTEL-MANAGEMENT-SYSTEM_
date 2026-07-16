package com.hotel.feignclient;

import com.hotel.dto.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// Define the Feign client to interact with the Notification microservice
@FeignClient(name = "NotificationMicroservice", url = "http://localhost:8003")
public interface NotificationFeignClient {

    // Method to retrieve all notifications from the Notification microservice
    @GetMapping("/notifications")
    List<NotificationDTO> getAllNotifications();

    // Method to retrieve a notification by its ID from the Notification microservice
    @GetMapping("/notifications/{id}")
    NotificationDTO getNotificationById(@PathVariable int id);
}
