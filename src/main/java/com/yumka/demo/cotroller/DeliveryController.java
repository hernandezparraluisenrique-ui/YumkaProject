package com.yumka.demo.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yumka.demo.dto.DeliveryRequest;
import com.yumka.demo.dto.DeliveryResponse;
import com.yumka.demo.service.DeliveryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService service;

    // 🔹 Crear delivery
    @PostMapping
    public DeliveryResponse create(@RequestBody @Valid DeliveryRequest req) {
        return service.create(req);
    }

    // 🔹 Actualizar estado
    @PutMapping("/{id}/status")
    public DeliveryResponse updateStatus(
            @PathVariable UUID id,
            @RequestParam String status
    ) {
        return service.updateStatus(id, status);
    }

    // 🔹 Buscar por orden
    @GetMapping("/order/{orderId}")
    public DeliveryResponse findByOrder(@PathVariable UUID orderId) {
        return service.findByOrder(orderId);
    }

    // 🔹 Pendientes
    @GetMapping("/pending")
    public List<DeliveryResponse> pending() {
        return service.findPending();
    }
}
