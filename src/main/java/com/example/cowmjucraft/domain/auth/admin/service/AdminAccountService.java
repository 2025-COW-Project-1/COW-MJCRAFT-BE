package com.example.cowmjucraft.domain.auth.admin.service;

import com.example.cowmjucraft.domain.account.entity.Role;
import com.example.cowmjucraft.domain.account.entity.User;
import com.example.cowmjucraft.domain.account.repository.UserRepository;
import com.example.cowmjucraft.domain.auth.admin.dto.request.AdminAccountUpdateRequest;
import com.example.cowmjucraft.domain.auth.login.dto.response.AdminLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminAccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminLoginResponseDto updateAdminAccount(AdminAccountUpdateRequest request) {
        User user = userRepository.findByUserId(request.currentUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (user.getRole() != Role.ADMIN) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin only");
        }

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        if (!request.currentUserId().equals(request.newUserId())
                && userRepository.existsByUserId(request.newUserId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicated userId");
        }

        if (!request.currentUserId().equals(request.newUserId())) {
            user.updateUserId(request.newUserId());
        }

        if (StringUtils.hasText(request.newPassword())) {
            user.updatePassword(passwordEncoder.encode(request.newPassword()));
        }

        return AdminLoginResponseDto.from(user);
    }
}
