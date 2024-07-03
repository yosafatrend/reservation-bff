package com.bff.reservation.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bff.reservation.common.model.Role;
import com.bff.reservation.common.model.Users;
import com.bff.reservation.common.repository.UserRepository;
import com.bff.reservation.web.dto.WebUserRequest;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WebUserService {

    @Autowired
    private UserRepository userRepository;

    public Set<Users> findAllById(Set<Long> ids) {
        List<Users> usersList = userRepository.findAllById(ids);
        return new HashSet<>(usersList);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Users createUser(WebUserRequest userRequest) {
        Users user = new Users();
        user.setName(userRequest.getName());
        user.setNo_telp(userRequest.getNoTelp());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setRole(Role.user);
        return userRepository.save(user);
    }

    public Users updateUser(Long id, WebUserRequest userRequest) {
        Users user = userRepository.findById(id).orElseThrow();
        user.setName(userRequest.getName());
        user.setNo_telp(userRequest.getNoTelp());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setUpdated_at(new Date());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Users user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }
}
