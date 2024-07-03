package com.bff.reservation.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bff.reservation.common.model.Venues;

@Repository
public interface VenueRepository extends JpaRepository<Venues, Long> {

}
