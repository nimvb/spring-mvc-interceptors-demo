package com.nimvb.app.authentication.service;

import com.nimvb.app.authentication.model.Token;
import com.nimvb.app.authentication.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final TokenService tokenService;
    private final UserService userService;

    public User authorize(@NonNull Token token){
        Claims claims = tokenService.parse(token);
        if(claims.getExpiration().toInstant().isBefore(Instant.now())){
            return null;
        }
        String email = claims.getSubject();
        try {
            User user = userService.find(email);
            return user;
        } catch (Exception e) {
            return null;
        }

    }
}
