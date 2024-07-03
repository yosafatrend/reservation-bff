package com.bff.reservation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.common.model.Reservations;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.model.Venues;
import com.bff.reservation.common.resource.ResourceNotFoundException;
import com.bff.reservation.web.dto.WebReservationRequest;
import com.bff.reservation.web.dto.WebReservationResponse;
import com.bff.reservation.web.service.WebReservationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/web/reservations")
public class WebReservationController {

    @Autowired
    private WebReservationService reservationService;

    @GetMapping
    public List<Reservations> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservations> getReservationById(@PathVariable(value = "id") Long id) {
        Reservations reservation = reservationService.getReservationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
        return ResponseEntity.ok().body(reservation);
    }

    @PostMapping
    public WebReservationResponse createReservation(@RequestBody WebReservationRequest reservationRequest) {
        Reservations reservation = reservationService.createReservation(reservationRequest);
        return convertToResponse(reservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebReservationResponse> updateReservation(@PathVariable(value = "id") Long id, @RequestBody WebReservationRequest reservationRequest) {
        Reservations updatedReservation = reservationService.updateReservation(id, reservationRequest);
        return ResponseEntity.ok(convertToResponse(updatedReservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable(value = "id") Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    private WebReservationResponse convertToResponse(Reservations reservation) {
        WebReservationResponse reservationResponse = new WebReservationResponse();
        reservationResponse.setReservationId(reservation.getReservation_id());
        reservationResponse.setUserIds(reservation.getUsers().stream()
        .map(Users::getUser_id)
        .collect(Collectors.toSet()));
        reservationResponse.setVenueIds(reservation.getVenues().stream()
        .map(Venues::getVenue_id)
        .collect(Collectors.toSet()));
        reservationResponse.setReservationDate(reservation.getReservation_date());
        reservationResponse.setStartTime(reservation.getStart_time());
        reservationResponse.setEndTime(reservation.getEnd_time());
        reservationResponse.setStatus(reservation.getStatus());
        return reservationResponse;
    }   
}

