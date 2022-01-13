package com.raf.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Auth {

    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

}
