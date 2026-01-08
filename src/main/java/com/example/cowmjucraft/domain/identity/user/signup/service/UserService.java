package com.example.cowmjucraft.domain.identity.user.signup.service;

import com.example.cowmjucraft.domain.identity.user.signup.dto.response.UserSignupResponseDto;
import com.example.cowmjucraft.domain.identity.user.signup.dto.request.UserSignupRequestDto;

public interface UserService {
    UserSignupResponseDto signup(UserSignupRequestDto request);
}
