package com.bff.reservation.thirdparty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Venues;
import com.bff.reservation.common.repository.VenueRepository;
import com.bff.reservation.thirdparty.dto.TptVenueResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TptVenueService {

    @Autowired
    private VenueRepository venuesRepository;

    public List<TptVenueResponse> getAllVenues() {
        List<Venues> venues = venuesRepository.findAll();
        return venues.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private TptVenueResponse convertToResponse(Venues venue) {
        TptVenueResponse response = new TptVenueResponse();
        response.setVenueId(venue.getVenue_id());
        response.setName(venue.getName());
        response.setAddress(venue.getAddress());
        response.setFacilities(venue.getFacilities());
        response.setPricePerHour(venue.getPrice_per_hour());
        return response;
    }
}

