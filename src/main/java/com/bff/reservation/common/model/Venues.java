package com.bff.reservation.common.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class Venues {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long venue_id;
    
    private String name;
    private String address;
    private String facilities;
    private Double price_per_hour;
    @CreationTimestamp
    private Date created_at;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at; 

    @JsonIgnore
    @ManyToMany(mappedBy = "venues")
    private Set<Reservations> reservations;
    
    @Override
    public int hashCode() {
        return Objects.hash(venue_id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venues venue = (Venues) o;
        return Objects.equals(venue_id, venue.venue_id) &&
                Objects.equals(name, venue.name);
    }
}
