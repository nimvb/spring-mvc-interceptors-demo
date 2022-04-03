package com.nimvb.app.authentication.controller;

import com.nimvb.app.authentication.model.User;
import com.nimvb.app.authentication.request.SignupRequest;
import com.nimvb.app.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> all(HttpServletRequest request) {
        return ResponseEntity.ok(userService.find());
    }

    @GetMapping("/whoami")
    public ResponseEntity<String> whoami(Authentication authentication){
        return ResponseEntity.ok(((User) authentication.getPrincipal()).getEmail());

    }



    @DeleteMapping("/{email}")
    public ResponseEntity<?> delete(@PathVariable("email")String email){
        userService.delete(email);
        return ResponseEntity.noContent().build();
    }
}
