package com.yumka.demo.service;

import java.time.LocalDateTime;

import com.yumka.demo.model.UserRef;

import com.yumka.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
     private final GoogleAuthService googleService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    // 🔹 Login con Google
    @Override
    public String loginWithGoogle(String idToken) {

        var payload = googleService.verifyToken(idToken);

        String email = payload.getEmail();
        String name = (String) payload.get("name");

        // 🔹 Buscar o crear usuario
        UserRef user = userRepository.findByEmail(email)
                .orElseGet(() -> userRepository.save(
                        UserRef.builder()
                                .email(email)
                                .fullName(name)
                                .createdAt(LocalDateTime.now())
                                .build()
                ));

        // 🔹 Generar JWT
        return jwtService.generateToken(user);
    }
}
