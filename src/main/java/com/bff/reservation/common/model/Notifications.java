package com.bff.reservation.common.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Data
@Entity
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notification_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date notification_data;

    @Column(columnDefinition = "ENUM('unread', 'read')")
    @Enumerated(EnumType.STRING)
    private StatusNotification status;
}
