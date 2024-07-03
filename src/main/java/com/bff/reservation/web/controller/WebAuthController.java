package com.bff.reservation.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bff.reservation.common.dto.JwtAuthenticationResponse;
import com.bff.reservation.common.dto.RefreshTokenRequest;
import com.bff.reservation.common.dto.SignInRequest;
import com.bff.reservation.common.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/web/auth")
@RequiredArgsConstructor
public class WebAuthController {
    
    private final AuthenticationService authenticationService;
    
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> singIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
    
}
