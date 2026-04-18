package com.yumka.demo.cotroller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yumka.demo.dto.ProducerRequest;
import com.yumka.demo.dto.ProducerResponse;
import com.yumka.demo.service.ProducerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/producers")
@RequiredArgsConstructor

public class ProducerController {
     private final ProducerService service;

    @PostMapping
    @Operation(summary = "Create a producer")
    @ApiResponse(responseCode = "200", description = "Producer created successfully")
    public ResponseEntity<ProducerResponse> create(@Valid @RequestBody ProducerRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get producer by ID")
    public ProducerResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    @Operation(summary = "Get all producers with pagination")
    public List<ProducerResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return service.findAll(page, pageSize);
    }

    @GetMapping("/search")
    @Operation(summary = "Search producers by name")
    public List<ProducerResponse> searchByName(@RequestParam String name) {
        return service.searchByName(name);
    }

    @GetMapping("/location")
    @Operation(summary = "Find producers by location")
    public List<ProducerResponse> findByLocation(@RequestParam String location) {
        return service.findByLocation(location);
    }
}
