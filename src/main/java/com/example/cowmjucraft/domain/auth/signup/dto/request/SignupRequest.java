package com.example.cowmjucraft.domain.auth.signup.dto.request;

import lombok.Getter;

@Getter
public class SignupRequest {

    private String userId;
    private String password;
    private String email;
    private String userName;
}
