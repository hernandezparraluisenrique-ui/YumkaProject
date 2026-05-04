package com.yumka.demo.mapper;

import java.time.LocalDateTime;

import com.yumka.demo.dto.OrderStatusHistoryResponse;
import com.yumka.demo.model.OrderStatusHistory;
import com.yumka.demo.model.Order;


public class OrderStatusHistoryMapper {
     public static OrderStatusHistoryResponse toResponse(OrderStatusHistory entity) {
        if (entity == null) return null;

        return OrderStatusHistoryResponse.builder()
                .status(entity.getStatus())
                .changedAt(entity.getChangedAt())
                .build();
    }

    public static OrderStatusHistory toEntity(Order order, String status) {
        if (order == null || status == null) return null;

        return OrderStatusHistory.builder()
                .order(order)
                .status(status)
                .changedAt(LocalDateTime.now())
                .build();
    }

    public static void copyToEntity(OrderStatusHistory entity, String status) {
        if (entity == null || status == null) return;

        entity.setStatus(status);
        entity.setChangedAt(LocalDateTime.now());
    }
}
