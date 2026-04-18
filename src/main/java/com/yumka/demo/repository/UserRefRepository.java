package com.yumka.demo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yumka.demo.model.UserRef;

@Repository
public interface UserRefRepository extends JpaRepository<UserRef, UUID> {
Optional<UserRef> findByEmail(String email);

    boolean existsByEmail(String email);
}
