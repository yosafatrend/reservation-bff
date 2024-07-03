package com.bff.reservation.thirdparty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bff.reservation.thirdparty.dto.TptVenueResponse;
import com.bff.reservation.thirdparty.service.TptVenueService;

import java.util.List;

@RestController
@RequestMapping("/api/thirdparty/venues")
public class TptVenueController {

    @Autowired
    private TptVenueService thirdPartyVenueService;

    @GetMapping
    public ResponseEntity<List<TptVenueResponse>> getAllVenues() {
        List<TptVenueResponse> venues = thirdPartyVenueService.getAllVenues();
        return ResponseEntity.ok(venues);
    }
}

