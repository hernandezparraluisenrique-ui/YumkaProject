package com.yumka.demo.cotroller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.yumka.demo.dto.OrderResponse;
import com.yumka.demo.service.OrderService;
import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @RequestParam UUID userId,
            @RequestParam UUID addressId
    ) {
        return ResponseEntity.ok(service.createOrderFromCart(userId, addressId));
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> findByUser(@PathVariable UUID userId) {
        return service.findByUser(userId);
    }

    @GetMapping("/producer/{producerId}")
    public List<OrderResponse> findByProducer(@PathVariable UUID producerId) {
        return service.findByProducer(producerId);
    }
    }

