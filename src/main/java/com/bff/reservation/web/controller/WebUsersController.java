package com.bff.reservation.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bff.reservation.common.model.Users;
import com.bff.reservation.web.dto.WebUserRequest;
import com.bff.reservation.web.dto.WebUserResponse;
import com.bff.reservation.web.service.WebUserService;

import java.util.List;

@RestController
@RequestMapping("/api/web/users")
public class WebUsersController {

    @Autowired
    private WebUserService userService;

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") Long id) {
        Users user = userService.getUserById(id)
                .orElseThrow();
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public WebUserResponse createUser(@RequestBody WebUserRequest userRequest) {
        Users user = userService.createUser(userRequest);
        return convertToResponse(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebUserResponse> updateUser(@PathVariable(value = "id") Long id, @RequestBody WebUserRequest userRequest) {
        Users updatedUser = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(convertToResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private WebUserResponse convertToResponse(Users user) {
        WebUserResponse userResponse = new WebUserResponse();
        userResponse.setUserId(user.getUser_id());
        userResponse.setName(user.getName());
        userResponse.setNoTelp(user.getNo_telp());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        userResponse.setCreatedAt(user.getCreated_at());
        userResponse.setUpdatedAt(user.getUpdated_at());
        return userResponse;
    }
}
