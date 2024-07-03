package com.bff.reservation.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.mobile.dto.MobileReservationDetailResponse;
import com.bff.reservation.mobile.dto.MobileReservationRequest;
import com.bff.reservation.mobile.dto.MobileReservationResponse;
import com.bff.reservation.mobile.service.MobileReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/reservations")
public class MobileReservationController {

    @Autowired
    private MobileReservationService mobileReservationService;

    @GetMapping
    public ResponseEntity<List<MobileReservationResponse>> getUserReservations(Authentication authentication) {
        String username = authentication.getName();
        List<MobileReservationResponse> reservations = mobileReservationService.getUserReservations(username);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<MobileReservationDetailResponse> createReservation(
            Authentication authentication,
            @RequestBody MobileReservationRequest reservationRequest) {
        String username = authentication.getName();
        MobileReservationDetailResponse reservation = mobileReservationService.createReservation(username, reservationRequest);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MobileReservationDetailResponse> updateReservation(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody MobileReservationRequest reservationRequest) {
        String username = authentication.getName();
        MobileReservationDetailResponse reservation = mobileReservationService.updateReservation(username, id, reservationRequest);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(Authentication authentication, @PathVariable Long id) {
        String username = authentication.getName();
        mobileReservationService.cancelReservation(username, id);
        return ResponseEntity.noContent().build();
    }
}

