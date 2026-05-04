package com.yumka.demo.mapper;

import java.time.LocalDateTime;

import com.yumka.demo.dto.PaymentRequest;
import com.yumka.demo.dto.PaymentResponse;
import com.yumka.demo.model.Order;
import com.yumka.demo.model.Payment;


public class PaymentMapper {
    
    // 🔹 Entity → Response
    public static PaymentResponse toResponse(Payment entity) {
        if (entity == null) return null;

        return PaymentResponse.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .build();
    }

    // 🔹 Request → Entity
    public static Payment toEntity(PaymentRequest dto, Order order) {
        if (dto == null) return null;

        return Payment.builder()
                .order(order)
                .amount(order.getTotal())
                .status("COMPLETED")
                .method(dto.getMethod())
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 🔹 Update Entity
    public static void copyToEntity(PaymentRequest dto, Payment entity, Order order) {
        if (dto == null || entity == null) return;

        entity.setOrder(order);
        entity.setMethod(dto.getMethod());
    }
}
