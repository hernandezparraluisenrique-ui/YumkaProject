package com.yumka.demo.cotroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yumka.demo.dto.ProductRequest;
import com.yumka.demo.dto.ProductResponse;
import com.yumka.demo.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
     private final ProductService service;

    @PostMapping
    @Operation(summary = "Create product")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping
    @Operation(summary = "Get all active products")
    public List<ProductResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/search")
    @Operation(summary = "Search products")
    public List<ProductResponse> search(@RequestParam String name) {
        return service.search(name);
    }

    @GetMapping("/price")
    @Operation(summary = "Filter by price")
    public List<ProductResponse> filterByPrice(@RequestParam Double min, @RequestParam Double max) {
        return service.filterByPrice(min, max);
    }

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate product")
    public void deactivate(@PathVariable UUID id) {
        service.deactivate(id);
    }
}
