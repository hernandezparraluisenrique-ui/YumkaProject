package com.yumka.demo.cotroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yumka.demo.dto.CategoryRequest;
import com.yumka.demo.dto.CategoryResponse;
import com.yumka.demo.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping
    @Operation(summary = "Create category")
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping
    @Operation(summary = "Get all categories")
    public List<CategoryResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/search")
    @Operation(summary = "Search categories")
    public List<CategoryResponse> search(@RequestParam String name) {
        return service.search(name);
    }
}
