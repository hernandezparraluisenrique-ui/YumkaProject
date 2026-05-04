package com.yumka.demo.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yumka.demo.dto.PaymentRequest;
import com.yumka.demo.dto.PaymentResponse;
import com.yumka.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
     private final PaymentService service;

    // 🔹 Crear pago
    @PostMapping
    public PaymentResponse create(@RequestBody @Valid PaymentRequest req) {
        return service.create(req);
    }

    // 🔹 Buscar por orden
    @GetMapping("/order/{orderId}")
    public PaymentResponse findByOrder(@PathVariable UUID orderId) {
        return service.findByOrder(orderId);
    }

    // 🔹 Pagos por usuario
    @GetMapping("/user/{userId}")
    public List<PaymentResponse> findByUser(@PathVariable UUID userId) {
        return service.findByUser(userId);
    }

    // 🔹 Pagos por productor 🔥
    @GetMapping("/producer/{producerId}")
    public List<PaymentResponse> findByProducer(@PathVariable UUID producerId) {
        return service.findByProducer(producerId);
    }

    // 🔹 Total global
    @GetMapping("/total")
    public Double total() {
        return service.totalRevenue();
    }

    // 🔹 Total por productor
    @GetMapping("/total/producer/{producerId}")
    public Double totalByProducer(@PathVariable UUID producerId) {
        return service.totalByProducer(producerId);
    }
}

