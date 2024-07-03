package com.bff.reservation.mobile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.repository.UserRepository;
import com.bff.reservation.mobile.dto.MobileUserProfileRequest;
import com.bff.reservation.mobile.dto.MobileUserProfileResponse;

@Service
public class MobileUserService {
    @Autowired
    private UserRepository userRepository;

    public MobileUserProfileResponse getUserProfile(String username){
        Users user = userRepository.findByEmail(username).orElseThrow(() 
        -> new RuntimeException("User not found"));

        return convertToResponse(user);
    }

    public MobileUserProfileResponse updateUserProfile(String username, MobileUserProfileRequest userProfileRequest) {
        Users user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userProfileRequest.getName());
        user.setNo_telp(userProfileRequest.getNoTelp());
        user.setEmail(userProfileRequest.getEmail());
        Users updatedUser = userRepository.save(user);
        return convertToResponse(updatedUser);
    }

    private MobileUserProfileResponse convertToResponse(Users user){
        MobileUserProfileResponse response = new MobileUserProfileResponse();
        response.setUserId(user.getUser_id());
        response.setName(user.getName());
        response.setNoTelp(user.getNo_telp());
        response.setEmail(user.getEmail());
        return response;
    }
}
