package com.yumka.demo.service;

import org.springframework.stereotype.Service;

import com.yumka.demo.dto.AuthRequest;
import com.yumka.demo.dto.AuthResponse;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface  AuthService {

     AuthResponse login(AuthRequest request);
}


