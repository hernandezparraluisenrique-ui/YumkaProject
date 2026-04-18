package com.yumka.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.yumka.demo.dto.FavoriteRequest;
import com.yumka.demo.dto.FavoriteResponse;
import com.yumka.demo.mapper.FavoriteMapper;
import com.yumka.demo.model.Favorite;
import com.yumka.demo.repository.FavoriteRepository;
import com.yumka.demo.repository.ProductRepository;
import com.yumka.demo.repository.UserRefRepository;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{
        private final FavoriteRepository repository;
    private final UserRefRepository userRepository;
    private final ProductRepository productRepository;

    // 🔥 Agregar favorito
    @Override
    public FavoriteResponse add(FavoriteRequest request) {

        if (repository.existsByUserIdAndProductId(request.getUserId(), request.getProductId())) {
            throw new IllegalStateException("Product already in favorites");
        }

        Favorite entity = Favorite.builder()
                .user(userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found")))
                .product(productRepository.findById(request.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found")))
                .createdAt(LocalDateTime.now())
                .build();

        return FavoriteMapper.toResponse(repository.save(entity));
    }

    // 🔥 Obtener favoritos
    @Override
    public List<FavoriteResponse> getByUser(UUID userId) {

        return repository.findByUserId(userId)
                .stream()
                .map(FavoriteMapper::toResponse)
                .toList();
    }

    // 🔥 Eliminar favorito
    @Override
    public void remove(UUID userId, UUID productId) {

        if (!repository.existsByUserIdAndProductId(userId, productId)) {
            throw new EntityNotFoundException("Favorite not found");
        }

        repository.deleteByUserIdAndProductId(userId, productId);
    }

    // 🔥 Verificar existencia
    @Override
    public boolean exists(UUID userId, UUID productId) {
        return repository.existsByUserIdAndProductId(userId, productId);
    }

    // 🔥 Contar favoritos por producto
    @Override
    public Long countByProduct(UUID productId) {
        return repository.countByProductId(productId);
    }
}
