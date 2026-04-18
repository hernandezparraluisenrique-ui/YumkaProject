package com.yumka.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yumka.demo.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, UUID>{
     Optional<Producer> findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);

    @Query("SELECT p FROM Producer p WHERE LOWER(p.businessName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Producer> searchByBusinessName(@Param("name") String name);

    @Query("SELECT p FROM Producer p WHERE p.location = :location")
    List<Producer> findByLocation(@Param("location") String location);
    
}
