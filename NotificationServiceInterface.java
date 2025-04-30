package com.hotel.service;

import com.hotel.dto.NotificationDTO;
import java.util.List;

public interface NotificationServiceInterface {

    List<NotificationDTO> getAllNotifications();

    NotificationDTO getNotificationById(int notificationId);

    NotificationDTO updateNotificationStatus(int notificationId, String status);

    boolean deleteNotification(int notificationId);

	NotificationDTO createNotification(NotificationDTO notificationDTO);
}
