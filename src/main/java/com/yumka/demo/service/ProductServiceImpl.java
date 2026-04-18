package com.yumka.demo.service;

import java.math.BigDecimal;
import com.yumka.demo.dto.ProductRequest;
import com.yumka.demo.dto.ProductResponse;
import com.yumka.demo.mapper.ProductMapper;
import com.yumka.demo.model.Producer;
import com.yumka.demo.model.Product;
import com.yumka.demo.repository.ProducerRepository;
import com.yumka.demo.repository.ProductRepository;
import com.yumka.demo.repository.CategoryRepository;
import com.yumka.demo.model.Category;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
     private final ProductRepository repository;
    private final ProducerRepository producerRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse create(ProductRequest request) {

        Producer producer = producerRepository.findById(request.getProducerId())
                .orElseThrow(() -> new EntityNotFoundException("Producer not found"));

        List<Category> categories = categoryRepository.findAllById(request.getCategoryIds());

        Product entity = ProductMapper.toEntity(request, producer, categories);

        return ProductMapper.toResponse(repository.save(entity));
    }

    @Override
    public List<ProductResponse> findAll() {
        return repository.findAllActive()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> search(String name) {
        return repository.searchByName(name)
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> filterByPrice(Double min, Double max) {
        return repository.filterByPrice(BigDecimal.valueOf(min), BigDecimal.valueOf(max))
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public void deactivate(UUID id) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        entity.setIsActive(false);
        repository.save(entity);
    }
    
}
