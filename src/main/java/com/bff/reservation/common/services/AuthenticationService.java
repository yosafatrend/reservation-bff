package com.bff.reservation.common.services;

import com.bff.reservation.common.dto.JwtAuthenticationResponse;
import com.bff.reservation.common.dto.RefreshTokenRequest;
import com.bff.reservation.common.dto.SignInRequest;
import com.bff.reservation.common.dto.SignUpRequest;
import com.bff.reservation.common.model.Users;

public interface AuthenticationService {
    
    Users signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
