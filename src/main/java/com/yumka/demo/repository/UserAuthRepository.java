package com.yumka.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.yumka.demo.model.UserAuth;

@Repository
public interface UserAuthRepository extends MongoRepository<UserAuth, String> {
    Optional<UserAuth> findByGoogleId(String googleId);
    Optional<UserAuth> findByEmail(String email);

    boolean existsByEmail(String email);
    
}
