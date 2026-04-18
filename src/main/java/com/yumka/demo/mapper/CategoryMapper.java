package com.yumka.demo.mapper;

import com.yumka.demo.dto.CategoryRequest;
import com.yumka.demo.dto.CategoryResponse;
import com.yumka.demo.model.Category;


public class CategoryMapper {
    public static CategoryResponse toResponse(Category entity) {
        if (entity == null) return null;

        return CategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static Category toEntity(CategoryRequest dto) {
        if (dto == null) return null;

        return Category.builder()
                .name(dto.getName())
                .build();
    }
}
