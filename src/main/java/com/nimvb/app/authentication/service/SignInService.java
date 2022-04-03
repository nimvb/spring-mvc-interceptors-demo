package com.nimvb.app.authentication.service;

import com.nimvb.app.authentication.model.Token;
import com.nimvb.app.authentication.model.User;
import com.nimvb.app.authentication.request.SingInRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SignInService {
    private final UserService userService;
    private final TokenService tokenService;


    public Token signIn(@NonNull SingInRequest request){
        User user = userService.find(request.getEmail());
        if(!request.getPassword().equals(user.getPassword())){
            throw new RuntimeException();
        }
        return tokenService.create(user, 60L * 60L * 60L * 1000L, new HashMap<>(Map.of("first_name",user.getFirstName())));
    }
}
