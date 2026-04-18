package com.yumka.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.yumka.demo.model.Favorite;
import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, UUID>{
    // 🔹 Verificar si ya existe un favorito (evita duplicados)
    boolean existsByUserIdAndProductId(UUID userId, UUID productId);

    // 🔹 Obtener favoritos por usuario
    @Query("SELECT f FROM Favorite f WHERE f.user.id = :userId")
    List<Favorite> findByUserId(@Param("userId") UUID userId);

    // 🔹 Eliminar favorito por usuario y producto
    @Modifying
    @Transactional
    @Query("DELETE FROM Favorite f WHERE f.user.id = :userId AND f.product.id = :productId")
    void deleteByUserIdAndProductId(
            @Param("userId") UUID userId,
            @Param("productId") UUID productId
    );

    // 🔥 EXTRA PRO (muy útil)

    // 🔹 Contar cuántas veces un producto está en favoritos
    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.product.id = :productId")
    Long countByProductId(@Param("productId") UUID productId);

    // 🔹 Obtener favoritos por producto (para analytics)
    @Query("SELECT f FROM Favorite f WHERE f.product.id = :productId")
    List<Favorite> findByProductId(@Param("productId") UUID productId);
}
