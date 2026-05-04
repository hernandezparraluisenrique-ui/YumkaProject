package com.yumka.demo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yumka.demo.model.Address;



@Repository
public interface  AddressRepository extends JpaRepository<Address, UUID> {
     // 🔹 Direcciones por usuario
    @Query("""
        SELECT a FROM Address a
        WHERE a.user.id = :userId
        ORDER BY a.createdAt DESC
    """)
    List<Address> findByUserId(UUID userId);

    // 🔹 Buscar por ciudad
    List<Address> findByCity(String city);

    // 🔹 Buscar por código postal
    List<Address> findByPostalCode(String postalCode);

    // 🔹 Verificar si el usuario tiene direcciones
    boolean existsByUserId(UUID userId);

    // 🔹 Conteo por usuario
    long countByUserId(UUID userId);
}
