package com.bff.reservation.thirdparty.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.bff.reservation.thirdparty.config.TptAuthenticationMapper;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TptAuthenticationService {
    private static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";
    private static final String AUTH_TOKEN = "be521d5c-0b62-48f9-999c-e1a2367a023f";

    public static Authentication getAuthentication(HttpServletRequest request){
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if(apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new TptAuthenticationMapper(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
