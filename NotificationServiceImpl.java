package com.hotel.service;

import com.hotel.dto.NotificationDTO;
import com.hotel.entity.Notification;
import com.hotel.exception.NotificationNotFoundException;
import com.hotel.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationServiceInterface {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO getNotificationById(int notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found"));
        return convertToDTO(notification);
    }

    @Override
    public NotificationDTO updateNotificationStatus(int notificationId, String status) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found"));
        notification.setStatus(status);
        Notification updatedNotification = notificationRepository.save(notification);
        return convertToDTO(updatedNotification);
    }

    @Override
    public boolean deleteNotification(int notificationId) {
        if (notificationRepository.existsById(notificationId)) {
            notificationRepository.deleteById(notificationId);
            return true;
        }
        throw new NotificationNotFoundException("Notification not found");
    }

    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationId(notification.getNotificationId());
        notificationDTO.setItemId(notification.getItemId());
        notificationDTO.setType(notification.getType());
        notificationDTO.setMessage(notification.getMessage());
        notificationDTO.setStatus(notification.getStatus());
        return notificationDTO;
    }

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setItemId(notificationDTO.getItemId());
        notification.setType(notificationDTO.getType());
        notification.setMessage(notificationDTO.getMessage());
        notification.setStatus(notificationDTO.getStatus());
        
        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }
}
