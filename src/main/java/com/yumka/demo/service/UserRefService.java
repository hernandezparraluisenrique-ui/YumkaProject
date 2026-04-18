package com.yumka.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.yumka.demo.model.UserRef;
import com.yumka.demo.repository.UserRefRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRefService {
    private final UserRefRepository repository;

    public UserRef createIfNotExists(String email, String name) {

        return repository.findByEmail(email)
                .orElseGet(() -> repository.save(
                        UserRef.builder()
                                .email(email)
                                .fullName(name)
                                .createdAt(LocalDateTime.now())
                                .build()
                ));
    }
}
