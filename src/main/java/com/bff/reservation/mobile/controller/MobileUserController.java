package com.bff.reservation.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.mobile.dto.MobileUserProfileRequest;
import com.bff.reservation.mobile.dto.MobileUserProfileResponse;
import com.bff.reservation.mobile.service.MobileUserService;

@RestController
@RequestMapping("/api/mobile/users")
public class MobileUserController {

    @Autowired
    private MobileUserService mobileUserService;

    @GetMapping("/profile")
    public ResponseEntity<MobileUserProfileResponse> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            MobileUserProfileResponse userProfile = mobileUserService.getUserProfile(username);
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.status(401).body(null); // Return 401 Unauthorized if not authenticated
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<MobileUserProfileResponse> updateUserProfile(@RequestBody MobileUserProfileRequest userProfileRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String username = authentication.getName();
            MobileUserProfileResponse updatedUserProfile = mobileUserService.updateUserProfile(username, userProfileRequest);
            return ResponseEntity.ok(updatedUserProfile);
        } else {
            return ResponseEntity.status(401).body(null); // Return 401 Unauthorized if not authenticated
        }
    }
}