package com.bff.reservation.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bff.reservation.common.model.Payments;
import com.bff.reservation.common.model.Users;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {
    List<Payments> findByReservation_Users(Users user);
}

