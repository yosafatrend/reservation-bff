package com.bff.reservation.thirdparty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.thirdparty.dto.TPtUserRequest;
import com.bff.reservation.thirdparty.dto.TptUserResponse;
import com.bff.reservation.thirdparty.service.TptUserService;

@RestController
@RequestMapping("/api/thirdparty/users")
public class TptUserController {

    @Autowired
    private TptUserService thirdPartyUserService;

    @PostMapping
    public ResponseEntity<TptUserResponse> integrateNewUser(@RequestBody TPtUserRequest userRequestDTO) {
        TptUserResponse userResponseDTO = thirdPartyUserService.integrateNewUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }
}

