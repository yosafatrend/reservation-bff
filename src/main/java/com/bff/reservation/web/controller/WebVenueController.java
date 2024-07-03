package com.bff.reservation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.common.model.Venues;
import com.bff.reservation.common.resource.ResourceNotFoundException;
import com.bff.reservation.web.dto.WebVenueRequest;
import com.bff.reservation.web.dto.WebVenueResponse;
import com.bff.reservation.web.service.WebVenueService;

import java.util.List;

@RestController
@RequestMapping("/api/web/venues")
public class WebVenueController {

    @Autowired
    private WebVenueService venueService;

    @GetMapping
    public List<Venues> getAllVenues() {
        return venueService.getAllVenues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venues> getVenueById(@PathVariable(value = "id") Long id) {
        Venues venue = venueService.getVenueById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venue not found with id " + id));
        return ResponseEntity.ok().body(venue);
    }

    @PostMapping
    public WebVenueResponse createVenue(@RequestBody WebVenueRequest venueRequest) {
        Venues venue = venueService.createVenue(venueRequest);
        return convertToResponse(venue);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<WebVenueResponse> updateVenue(@PathVariable(value = "id") Long id, @RequestBody WebVenueRequest venueRequest) {
        Venues updatedVenue = venueService.updateVenue(id, venueRequest);
        return ResponseEntity.ok(convertToResponse(updatedVenue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable(value = "id") Long id) {
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build();
    }

    private WebVenueResponse convertToResponse(Venues venue) {
        WebVenueResponse venueResponse = new WebVenueResponse();
        venueResponse.setVenueId(venue.getVenue_id());
        venueResponse.setName(venue.getName());
        venueResponse.setAddress(venue.getAddress());
        venueResponse.setFacilities(venue.getFacilities());
        venueResponse.setPricePerHour(venue.getPrice_per_hour());
        venueResponse.setCreatedAt(venue.getCreated_at());
        venueResponse.setUpdatedAt(venue.getUpdated_at());
        return venueResponse;
    }
}
