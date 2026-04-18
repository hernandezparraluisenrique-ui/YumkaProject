package com.yumka.demo.cotroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yumka.demo.dto.FavoriteRequest;
import com.yumka.demo.dto.FavoriteResponse;
import com.yumka.demo.service.FavoriteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/favorites")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService service;

    // 🔹 Agregar favorito
    @PostMapping
    @Operation(summary = "Add product to favorites")
    @ApiResponse(responseCode = "200", description = "Product added to favorites")
    public ResponseEntity<FavoriteResponse> add(@Valid @RequestBody FavoriteRequest req) {
        return ResponseEntity.ok(service.add(req));
    }

    // 🔹 Obtener favoritos por usuario
    @GetMapping("/{userId}")
    @Operation(summary = "Get favorites by user")
    @ApiResponse(responseCode = "200", description = "List of favorites")
    public List<FavoriteResponse> getByUser(@PathVariable UUID userId) {
        return service.getByUser(userId);
    }

    // 🔹 Eliminar favorito
    @DeleteMapping
    @Operation(summary = "Remove product from favorites")
    @ApiResponse(responseCode = "200", description = "Favorite removed")
    public void remove(
            @RequestParam UUID userId,
            @RequestParam UUID productId
    ) {
        service.remove(userId, productId);
    }

    // 🔥 Verificar si ya es favorito
    @GetMapping("/exists")
    @Operation(summary = "Check if product is in favorites")
    public boolean exists(
            @RequestParam UUID userId,
            @RequestParam UUID productId
    ) {
        return service.exists(userId, productId);
    }

    // 🔥 Contar favoritos de un producto
    @GetMapping("/count/{productId}")
    @Operation(summary = "Count favorites by product")
    public Long countByProduct(@PathVariable UUID productId) {
        return service.countByProduct(productId);
    }
}
