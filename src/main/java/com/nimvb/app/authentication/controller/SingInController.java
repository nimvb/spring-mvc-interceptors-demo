package com.nimvb.app.authentication.controller;

import com.nimvb.app.authentication.model.Token;
import com.nimvb.app.authentication.request.SingInRequest;
import com.nimvb.app.authentication.service.SignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class SingInController {
    private final SignInService signInService;

    @PostMapping
    public ResponseEntity<Token> singIn(@RequestBody SingInRequest request){
        return ResponseEntity.ok(signInService.signIn(request));
    }
}
