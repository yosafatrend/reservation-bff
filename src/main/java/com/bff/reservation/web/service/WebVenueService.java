package com.bff.reservation.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Venues;
import com.bff.reservation.common.repository.VenueRepository;
import com.bff.reservation.common.resource.ResourceNotFoundException;
import com.bff.reservation.web.dto.WebVenueRequest;

import java.util.Date;  
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WebVenueService {

    @Autowired
    private VenueRepository venueRepository;

    public Set<Venues> findAllById(Set<Long> ids) {
        List<Venues> venuesList = venueRepository.findAllById(ids);
        return new HashSet<>(venuesList);
    }

    public List<Venues> getAllVenues() {
        return venueRepository.findAll();
    }

    public Optional<Venues> getVenueById(Long id) {
        return venueRepository.findById(id);
    }

    public Venues createVenue(WebVenueRequest venueRequest) {
        Venues venue = new Venues();
        venue.setName(venueRequest.getName());
        venue.setAddress(venueRequest.getAddress());
        venue.setFacilities(venueRequest.getFacilities());
        venue.setPrice_per_hour(venueRequest.getPricePerHour());
        return venueRepository.save(venue);
    }

    public Venues updateVenue(Long id, WebVenueRequest venueRequest) {
        Venues venue = venueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venue not found"));
        venue.setName(venueRequest.getName());
        venue.setAddress(venueRequest.getAddress());
        venue.setFacilities(venueRequest.getFacilities());
        venue.setPrice_per_hour(venueRequest.getPricePerHour());
        venue.setUpdated_at(new Date());
        return venueRepository.save(venue);
    }

    public void deleteVenue(Long id) {
        Venues venue = venueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venue not found"));
        venueRepository.delete(venue);
    }
}

