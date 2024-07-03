package com.bff.reservation.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Reservations;
import com.bff.reservation.common.model.StatusReservation;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.model.Venues;
import com.bff.reservation.common.repository.ReservationRepository;
import com.bff.reservation.common.repository.UserRepository;
import com.bff.reservation.common.repository.VenueRepository;
import com.bff.reservation.mobile.dto.MobileReservationDetailResponse;
import com.bff.reservation.mobile.dto.MobileReservationRequest;
import com.bff.reservation.mobile.dto.MobileReservationResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MobileReservationService {

    @Autowired
    private ReservationRepository reservationsRepository;

    @Autowired
    private VenueRepository venuesRepository;

    @Autowired
    private UserRepository usersRepository;

    public List<MobileReservationResponse> getUserReservations(String username) {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        List<Reservations> reservations = reservationsRepository.findByUsers(user);
        return reservations.stream()
                .map(this::convertToSummaryResponse)
                .collect(Collectors.toList());
    }

    public MobileReservationDetailResponse createReservation(String username, MobileReservationRequest reservationRequest) {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        Venues venue = venuesRepository.findById(reservationRequest.getVenueId()).orElseThrow(() -> new RuntimeException("Venue not found"));

        Reservations reservation = new Reservations();
        reservation.setUsers(Set.of(user));
        reservation.setVenues(Set.of(venue));
        reservation.setReservation_date(reservationRequest.getReservationDate());
        reservation.setStart_time(reservationRequest.getStartTime());
        reservation.setStart_time(reservationRequest.getEndTime());
        reservation.setStatus(StatusReservation.PENDING);

        Reservations savedReservation = reservationsRepository.save(reservation);
        return convertToDetailResponse(savedReservation);
    }

    public MobileReservationDetailResponse updateReservation(String username, Long id, MobileReservationRequest reservationRequest) {
        Reservations reservation = reservationsRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        Venues venue = venuesRepository.findById(reservationRequest.getVenueId()).orElseThrow(() -> new RuntimeException("Venue not found"));

        reservation.setUsers(Set.of(user));
        reservation.setVenues(Set.of(venue));
        reservation.setReservation_date(reservationRequest.getReservationDate());
        reservation.setStart_time(reservationRequest.getStartTime());
        reservation.setEnd_time(reservationRequest.getEndTime());
        reservation.setStatus(StatusReservation.PENDING);

        Reservations updatedReservation = reservationsRepository.save(reservation);
        return convertToDetailResponse(updatedReservation);
    }

    public void cancelReservation(String username, Long id) {
        Reservations reservation = reservationsRepository.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
        reservation.setStatus(StatusReservation.CANCELLED);
        reservationsRepository.save(reservation);
    }

    private MobileReservationResponse convertToSummaryResponse(Reservations reservation) {
        MobileReservationResponse response = new MobileReservationResponse();
        response.setReservationId(reservation.getReservation_id());
        response.setReservationDate(reservation.getReservation_date());
        response.setStartTime(reservation.getStart_time());
        response.setEndTime(reservation.getEnd_time());
        response.setStatus(reservation.getStatus().toString());
        return response;
    }

    private MobileReservationDetailResponse convertToDetailResponse(Reservations reservation) {
        MobileReservationDetailResponse response = new MobileReservationDetailResponse();
        response.setReservationId(reservation.getReservation_id());
        response.setReservationDate(reservation.getReservation_date());
        response.setStartTime(reservation.getStart_time());
        response.setEndTime(reservation.getEnd_time());
        response.setStatus(reservation.getStatus().toString());

        Venues venue = reservation.getVenues().iterator().next();
        response.setVenueName(venue.getName());
        response.setVenueAddress(venue.getAddress());
        response.setVenuePricePerHour(venue.getPrice_per_hour());

        return response;
    }
}

