package com.bff.reservation.thirdparty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Role;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.repository.UserRepository;
import com.bff.reservation.thirdparty.dto.TPtUserRequest;
import com.bff.reservation.thirdparty.dto.TptUserResponse;

@Service
public class TptUserService {

    @Autowired
    private UserRepository usersRepository;

    public TptUserResponse integrateNewUser(TPtUserRequest userRequestDTO) {
        Users user = new Users();
        user.setName(userRequestDTO.getName());
        user.setNo_telp(userRequestDTO.getNoTelp());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword()); // Ensure password is encoded
        user.setRole(Role.user);
        Users savedUser = usersRepository.save(user);
        return convertToResponse(savedUser);
    }

    private TptUserResponse convertToResponse(Users user) {
        TptUserResponse response = new TptUserResponse();
        response.setUserId(user.getUser_id());
        response.setName(user.getName());
        response.setNoTelp(user.getNo_telp());
        response.setEmail(user.getEmail());
        return response;
    }
}

