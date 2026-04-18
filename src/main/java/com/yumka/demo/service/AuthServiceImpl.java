package com.yumka.demo.service;

import java.time.LocalDateTime;

import com.yumka.demo.dto.AuthRequest;
import com.yumka.demo.dto.AuthResponse;
import com.yumka.demo.model.UserAuth;
import com.yumka.demo.repository.UserAuthRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import com.yumka.demo.model.UserRef;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserAuthRepository repository;
    private final JwtService jwtService;
    private final UserRefService userRefService;

    @Override
    @Transactional
    public AuthResponse login(AuthRequest request) {

        UserAuth user = repository.findByGoogleId(request.getGoogleId())
                .orElseGet(() -> {
                    UserAuth newUser = UserAuth.builder()
                            .googleId(request.getGoogleId())
                            .email(request.getEmail())
                            .name(request.getName())
                            .photo(request.getPhoto())
                            .role("USER")
                            .createdAt(LocalDateTime.now())
                            .build();
                    return repository.save(newUser);
                });

        // 🔥 sincroniza con PostgreSQL
        userRefService.createIfNotExists(user.getEmail(), user.getName());

        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .role(user.getRole())
                .build();
    }
}
