package com.nimvb.app.authentication.controller;

import com.nimvb.app.authentication.model.User;
import com.nimvb.app.authentication.request.SignupRequest;
import com.nimvb.app.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registrations")
public class SignupController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SignupRequest request) {
        userService.create(User.of(request.getEmail(),
                request.getFirstName(),
                request.getLastName(),
                request.getPassword()));
        return ResponseEntity.ok().build();
    }
}
