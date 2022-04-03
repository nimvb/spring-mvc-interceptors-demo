package com.nimvb.app.authentication.request;

import liquibase.pro.packaged.S;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class SingInRequest {
    private final String email;
    private final String password;
}
