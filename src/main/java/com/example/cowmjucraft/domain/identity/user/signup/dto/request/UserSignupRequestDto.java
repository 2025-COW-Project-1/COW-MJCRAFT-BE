package com.example.cowmjucraft.domain.identity.user.signup.dto.request;

import lombok.Getter;

@Getter
public class UserSignupRequestDto {

    private String userId;
    private String password;
    private String email;
    private String userName;
}
