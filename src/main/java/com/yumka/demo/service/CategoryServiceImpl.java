package com.yumka.demo.service;

import com.yumka.demo.dto.CategoryRequest;
import com.yumka.demo.dto.CategoryResponse;
import com.yumka.demo.mapper.CategoryMapper;
import com.yumka.demo.repository.CategoryRepository;
import com.yumka.demo.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
     private final CategoryRepository repository;

    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category entity = CategoryMapper.toEntity(request);
        return CategoryMapper.toResponse(repository.save(entity));
    }

    @Override
    public List<CategoryResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<CategoryResponse> search(String name) {
        return repository.searchByName(name)
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }
}
