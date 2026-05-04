package com.yumka.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yumka.demo.model.UserRef;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserRef, UUID>  {
       Optional<UserRef> findByEmail(String email);
}
