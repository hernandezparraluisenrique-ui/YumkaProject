package com.yumka.demo.cotroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yumka.demo.dto.AuthRequest;
import com.yumka.demo.dto.AuthResponse;
import com.yumka.demo.dto.GoogleLoginRequest;
import com.yumka.demo.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
private final AuthService authService;

    @PostMapping("/google")
    public Map<String, String> login(@RequestBody GoogleLoginRequest request) {

        String token = authService.loginWithGoogle(request.getIdToken());

        return Map.of("token", token);
    }
}
