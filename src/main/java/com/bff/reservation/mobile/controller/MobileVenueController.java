package com.bff.reservation.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.mobile.dto.MobileVenueDetailResponse;
import com.bff.reservation.mobile.dto.MobileVenueResponse;
import com.bff.reservation.mobile.service.MobileVenueService;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/venues")
public class MobileVenueController {

    @Autowired
    private MobileVenueService mobileVenueService;

    @GetMapping
    public ResponseEntity<List<MobileVenueResponse>> getAllVenues() {
        List<MobileVenueResponse> venues = mobileVenueService.getAllVenues();
        return ResponseEntity.ok(venues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MobileVenueDetailResponse> getVenueById(@PathVariable Long id) {
        MobileVenueDetailResponse venue = mobileVenueService.getVenueById(id);
        return ResponseEntity.ok(venue);
    }
}

