package com.yumka.demo.mapper;

import com.yumka.demo.dto.FavoriteResponse;
import com.yumka.demo.model.Favorite;



public class FavoriteMapper {
     public static FavoriteResponse toResponse(Favorite entity) {
        if (entity == null) return null;

        return FavoriteResponse.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .productId(entity.getProduct().getId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
