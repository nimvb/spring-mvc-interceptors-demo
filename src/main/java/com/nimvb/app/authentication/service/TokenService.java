package com.nimvb.app.authentication.service;

import com.nimvb.app.authentication.model.Token;
import com.nimvb.app.authentication.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.secret:P@ssw0rd}")
    private String secret;

    public Token create(@NonNull User user, Long validityTime, Map<String,Object> claims){

        Date issuedAt  = new Date(System.currentTimeMillis());
        Date         expiredAt = Date.from(issuedAt.toInstant().plusMillis(validityTime));
        String email = user.getEmail();
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(issuedAt)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();


        return Token.of(jwt);
    }

    public Claims parse(@NonNull Token token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.getValue())
                .getBody();
    }
}
