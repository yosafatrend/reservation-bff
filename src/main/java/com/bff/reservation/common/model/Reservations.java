package com.bff.reservation.common.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservation_id;
    
    @ManyToMany
    @JoinTable(
        name = "reservation_users",
        joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Users> users;

    @ManyToMany
    @JoinTable(
        name = "reservation_venues",
        joinColumns = @JoinColumn(name = "reservation_id"),
        inverseJoinColumns = @JoinColumn(name = "venue_id")
    )
    private Set<Venues> venues;
    
    private Date reservation_date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;

    @Temporal(TemporalType.TIMESTAMP)
    private Date end_time;

    @Column(columnDefinition = "ENUM('PENDING', 'CONFIRMED', 'CANCELLED')")
    @Enumerated(EnumType.STRING)
    private StatusReservation status;

    @JsonIgnore
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Set<Payments> payments;

    @Override
    public int hashCode() {
        return Objects.hash(reservation_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservations reservation = (Reservations) o;
        return Objects.equals(reservation_id, reservation.reservation_id);
    }

}
