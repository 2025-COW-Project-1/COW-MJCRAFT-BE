package com.example.cowmjucraft.domain.auth.signup.service;

import com.example.cowmjucraft.domain.auth.signup.dto.response.SignupResponse;
import com.example.cowmjucraft.domain.auth.signup.dto.request.SignupRequest;

public interface UserService {
    SignupResponse signup(SignupRequest request);
}