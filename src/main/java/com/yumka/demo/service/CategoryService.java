package com.yumka.demo.service;

import com.yumka.demo.dto.CategoryRequest;
import com.yumka.demo.dto.CategoryResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface CategoryService {
     CategoryResponse create(CategoryRequest request);

    List<CategoryResponse> findAll();

    List<CategoryResponse> search(String name);
}
