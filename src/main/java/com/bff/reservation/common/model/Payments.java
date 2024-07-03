package com.bff.reservation.common.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long payment_id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservations reservation;

    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date payment_date;

    private String payment_method;

    @Column(columnDefinition = "ENUM('unpaid', 'paid', 'refunded')")
    @Enumerated(EnumType.STRING)
    private StatusPayment status;

}
