package com.example.cowmjucraft.domain.auth.admin.controller;

import com.example.cowmjucraft.domain.auth.admin.dto.request.AdminAccountUpdateRequest;
import com.example.cowmjucraft.domain.auth.admin.service.AdminAccountService;
import com.example.cowmjucraft.domain.auth.login.dto.response.AdminLoginResponseDto;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminAccountController implements AdminAccountControllerDocs {

    private final AdminAccountService adminAccountService;

    @Override
    @PatchMapping("/account")
    public AdminLoginResponseDto updateAdminAccount(@Valid @RequestBody AdminAccountUpdateRequest request) {
        return adminAccountService.updateAdminAccount(request);
    }
}
