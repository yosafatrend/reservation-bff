package com.bff.reservation.mobile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bff.reservation.common.dto.JwtAuthenticationResponse;
import com.bff.reservation.common.dto.RefreshTokenRequest;
import com.bff.reservation.common.dto.SignInRequest;
import com.bff.reservation.common.dto.SignUpRequest;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/mobile/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Users> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }
    
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> singIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
    
}
