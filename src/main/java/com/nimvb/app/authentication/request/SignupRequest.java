package com.nimvb.app.authentication.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class SignupRequest {
    private final String email;
    private final String password;
    private final String firstName;
    private final String lastName;
}
