package com.bff.reservation.thirdparty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.thirdparty.dto.TptReservationRequest;
import com.bff.reservation.thirdparty.dto.TptReservationResponse;
import com.bff.reservation.thirdparty.service.TptReservationService;

@RestController
@RequestMapping("/api/thirdparty/reservations")
public class TptReservationController {

    @Autowired
    private TptReservationService thirdPartyReservationService;

    @PostMapping
    public ResponseEntity<TptReservationResponse> createNewReservation(@RequestBody TptReservationRequest reservationRequestDTO) {
        TptReservationResponse reservationResponseDTO = thirdPartyReservationService.createNewReservation(reservationRequestDTO);
        return ResponseEntity.ok(reservationResponseDTO);
    }
}

