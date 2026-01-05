package com.example.cowmjucraft.domain.auth.signup.controller;

import com.example.cowmjucraft.domain.auth.signup.dto.request.SignupRequest;
import com.example.cowmjucraft.domain.auth.signup.dto.response.SignupResponse;
import com.example.cowmjucraft.domain.auth.signup.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public SignupResponse signup(@RequestBody SignupRequest request){
        return userService.signup(request);
    }
}