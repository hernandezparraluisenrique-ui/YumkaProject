package com.yumka.demo.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


import com.yumka.demo.dto.ProductRequest;
import com.yumka.demo.dto.ProductResponse;
import com.yumka.demo.model.Producer;
import com.yumka.demo.model.Product;
import com.yumka.demo.model.Category;


public class ProductMapper {
     public static ProductResponse toResponse(Product entity) {
        if (entity == null) return null;

        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice().doubleValue())
                .stock(entity.getStock())
                .isActive(entity.getIsActive())
                .build();
    }

    public static Product toEntity(ProductRequest dto, Producer producer, List<Category> categories) {
        if (dto == null) return null;

        return Product.builder()
                .producer(producer)
                .name(dto.getName())
                .description(dto.getDescription())
                .price(BigDecimal.valueOf(dto.getPrice()))
                .stock(dto.getStock())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .categories(categories)
                .build();
    }

    public static void copyToEntity(ProductRequest dto, Product entity, Producer producer, List<Category> categories) {
        if (dto == null || entity == null) return;

        entity.setProducer(producer);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(BigDecimal.valueOf(dto.getPrice()));
        entity.setStock(dto.getStock());
        entity.setCategories(categories);
    }
    
}
