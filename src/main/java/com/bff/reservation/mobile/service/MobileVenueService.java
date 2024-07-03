package com.bff.reservation.mobile.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Venues;
import com.bff.reservation.common.repository.VenueRepository;
import com.bff.reservation.mobile.dto.MobileVenueDetailResponse;
import com.bff.reservation.mobile.dto.MobileVenueResponse;

@Service
public class MobileVenueService {
    
    @Autowired
    private VenueRepository venuesRepository;

    public List<MobileVenueResponse> getAllVenues() {
        List<Venues> venues = venuesRepository.findAll();
        return venues.stream()
                .map(this::convertToSummaryResponse)
                .collect(Collectors.toList());
    }

    public MobileVenueDetailResponse getVenueById(Long id) {
        Venues venue = venuesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found"));
        return convertToDetailResponse(venue);
    }

    private MobileVenueResponse convertToSummaryResponse(Venues venue) {
        MobileVenueResponse response = new MobileVenueResponse();
        response.setVenueId(venue.getVenue_id());
        response.setName(venue.getName());
        response.setAddress(venue.getAddress());
        response.setPricePerHour(venue.getPrice_per_hour());
        return response;
    }

    private MobileVenueDetailResponse convertToDetailResponse(Venues venue) {
        MobileVenueDetailResponse response = new MobileVenueDetailResponse();
        response.setVenueId(venue.getVenue_id());
        response.setName(venue.getName());
        response.setAddress(venue.getAddress());
        response.setFacilities(venue.getFacilities());
        response.setPricePerHour(venue.getPrice_per_hour());
        return response;
    }
}
