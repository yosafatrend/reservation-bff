package com.bff.reservation.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bff.reservation.common.model.Reservations;
import com.bff.reservation.common.model.Users;

public interface ReservationRepository extends JpaRepository<Reservations, Long> {
    List<Reservations> findByUsers(Users user);

}
