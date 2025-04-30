package com.hotel.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;



@Entity
@Table(name = "notifications")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private int notificationId;

    
    private int itemId;

    @NotEmpty(message = "Notification type is mandatory")
    @Column(name = "type")
    private String type; // Low Stock, Restocked, Empty, New Order

    @NotEmpty(message = "Message is mandatory")
    @Column(name = "message")
    private String message;

    @NotEmpty(message = "Status is mandatory")
    @Column(name = "status")
    private String status; // Sent, Pending, Read

    
    public Notification() {
    }
}