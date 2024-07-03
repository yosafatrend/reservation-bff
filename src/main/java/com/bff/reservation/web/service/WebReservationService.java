package com.bff.reservation.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Reservations;
import com.bff.reservation.common.repository.ReservationRepository;
import com.bff.reservation.common.resource.ResourceNotFoundException;
import com.bff.reservation.web.dto.WebReservationRequest;

import java.util.List;
import java.util.Optional;

@Service
public class WebReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private WebUserService userService;
    
    @Autowired  
    private WebVenueService venueService;

    public List<Reservations> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservations> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservations createReservation(WebReservationRequest reservationRequest) {
        Reservations reservation = new Reservations();
        reservation.setUsers(userService.findAllById(reservationRequest.getUserIds()));
        reservation.setVenues(venueService.findAllById(reservationRequest.getVenueIds()));
        reservation.setReservation_date(reservationRequest.getReservationDate());
        reservation.setStart_time(reservationRequest.getStartTime());
        reservation.setEnd_time(reservationRequest.getEndTime());
        reservation.setStatus(reservationRequest.getStatus());
        return reservationRepository.save(reservation);
    }
    
    public Reservations updateReservation(Long id, WebReservationRequest reservationRequest) {
        Reservations reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        reservation.setUsers(userService.findAllById(reservationRequest.getUserIds()));
        reservation.setVenues(venueService.findAllById(reservationRequest.getVenueIds()));
        reservation.setReservation_date(reservationRequest.getReservationDate());
        reservation.setStart_time(reservationRequest.getStartTime());
        reservation.setEnd_time(reservationRequest.getEndTime());
        reservation.setStatus(reservationRequest.getStatus());
        return reservationRepository.save(reservation);
    }
    

    public void deleteReservation(Long id) {
        Reservations reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        reservationRepository.delete(reservation);
    }
}

