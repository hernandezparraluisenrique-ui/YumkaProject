package com.yumka.demo.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yumka.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
    @Query("SELECT p FROM Product p WHERE p.isActive = true")
    List<Product> findAllActive();

    @Query("SELECT p FROM Product p WHERE p.isActive = true AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max AND p.isActive = true")
    List<Product> filterByPrice(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :categoryId AND p.isActive = true")
    List<Product> findByCategory(@Param("categoryId") UUID categoryId);
    
}
