package com.yumka.demo.service;

import org.springframework.stereotype.Service;

import com.yumka.demo.dto.FavoriteRequest;
import com.yumka.demo.dto.FavoriteResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public interface  FavoriteService  {
     // 🔹 Agregar a favoritos
    FavoriteResponse add(FavoriteRequest request);

    // 🔹 Obtener favoritos por usuario
    List<FavoriteResponse> getByUser(UUID userId);

    // 🔹 Eliminar favorito
    void remove(UUID userId, UUID productId);

    // 🔹 Verificar si existe
    boolean exists(UUID userId, UUID productId);

    // 🔹 Contar favoritos de un producto
    Long countByProduct(UUID productId);
}
