package com.bff.reservation.thirdparty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Reservations;
import com.bff.reservation.common.model.StatusReservation;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.model.Venues;
import com.bff.reservation.common.repository.ReservationRepository;
import com.bff.reservation.common.repository.UserRepository;
import com.bff.reservation.common.repository.VenueRepository;
import com.bff.reservation.thirdparty.dto.TptReservationRequest;
import com.bff.reservation.thirdparty.dto.TptReservationResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TptReservationService {

    @Autowired
    private ReservationRepository reservationsRepository;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private VenueRepository venuesRepository;

    public TptReservationResponse createNewReservation(TptReservationRequest reservationRequestDTO) {
        Reservations reservation = new Reservations();
        reservation.setReservation_date(reservationRequestDTO.getReservationDate());
        reservation.setStart_time(reservationRequestDTO.getStartTime());
        reservation.setEnd_time(reservationRequestDTO.getEndTime());
        reservation.setStatus(StatusReservation.PENDING);

        // Find users and venues by IDs
        Set<Users> users = usersRepository.findAllById(reservationRequestDTO.getUserIds()).stream().collect(Collectors.toSet());
        Set<Venues> venues = venuesRepository.findAllById(reservationRequestDTO.getVenueIds()).stream().collect(Collectors.toSet());

        reservation.setUsers(users);
        reservation.setVenues(venues);

        Reservations savedReservation = reservationsRepository.save(reservation);
        return convertToResponse(savedReservation);
    }

    private TptReservationResponse convertToResponse(Reservations reservation) {
        TptReservationResponse response = new TptReservationResponse();
        response.setReservationId(reservation.getReservation_id());
        response.setReservationDate(reservation.getReservation_date());
        response.setStartTime(reservation.getStart_time());
        response.setEndTime(reservation.getEnd_time());
        response.setStatus(reservation.getStatus().toString());
        response.setUserIds(reservation.getUsers().stream().map(Users::getUser_id).collect(Collectors.toSet()));
        response.setVenueIds(reservation.getVenues().stream().map(Venues::getVenue_id).collect(Collectors.toSet()));
        return response;
    }
}

