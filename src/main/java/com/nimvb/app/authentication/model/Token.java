package com.nimvb.app.authentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class Token {
    @JsonProperty("access_token")
    private final String value;
}
