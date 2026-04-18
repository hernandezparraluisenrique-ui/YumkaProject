package com.yumka.demo.cotroller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yumka.demo.dto.CartItemRequest;
import com.yumka.demo.dto.CartResponse;
import com.yumka.demo.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;



@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
     private final CartService service;

    @PostMapping
    @Operation(summary = "Add product to cart")
    public ResponseEntity<CartResponse> add(@Valid @RequestBody CartItemRequest req) {
        return ResponseEntity.ok(service.addProduct(req));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user cart")
    public CartResponse get(@PathVariable UUID userId) {
        return service.getCart(userId);
    }

    @DeleteMapping
    @Operation(summary = "Remove product from cart")
    public void remove(@RequestParam UUID userId, @RequestParam UUID productId) {
        service.removeProduct(userId, productId);
    }

    @PostMapping("/checkout/{userId}")
    @Operation(summary = "Checkout cart")
    public CartResponse checkout(@PathVariable UUID userId) {
        return service.checkout(userId);
    }
}
