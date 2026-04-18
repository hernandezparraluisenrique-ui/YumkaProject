package com.yumka.demo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yumka.demo.dto.ProductRequest;
import com.yumka.demo.dto.ProductResponse;

@Service
@Transactional
public interface ProductService {
    ProductResponse create(ProductRequest request);

    List<ProductResponse> findAll();

    List<ProductResponse> search(String name);

    List<ProductResponse> filterByPrice(Double min, Double max);

    void deactivate(UUID id);
}
