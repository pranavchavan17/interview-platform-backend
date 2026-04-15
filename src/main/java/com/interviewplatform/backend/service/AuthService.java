package com.interviewplatform.backend.service;

import com.interviewplatform.backend.dto.RegisterRequest;
import com.interviewplatform.backend.entity.User;
import com.interviewplatform.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // hashing
                .role(request.getRole())
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }
}
