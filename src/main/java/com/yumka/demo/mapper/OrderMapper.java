package com.yumka.demo.mapper;

import java.time.LocalDateTime;

import com.yumka.demo.dto.OrderRequest;
import com.yumka.demo.dto.OrderResponse;
import com.yumka.demo.model.Address;
import com.yumka.demo.model.Order;
import com.yumka.demo.model.UserRef;

public class OrderMapper {
 public static OrderResponse toResponse(Order entity) {
        if (entity == null) return null;

        return OrderResponse.builder()
                .orderId(entity.getId())
                .total(entity.getTotal() != null ? entity.getTotal().doubleValue() : 0.0)
                .status(entity.getStatus())
                .build();
    }

    public static Order toEntity(OrderRequest dto, UserRef user, Address address) {
        if (dto == null) return null;

        return Order.builder()
                .user(user)
                .address(address)
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static void copyToEntity(OrderRequest dto, Order entity, UserRef user, Address address) {
        if (dto == null || entity == null) return;

        entity.setUser(user);
        entity.setAddress(address);
    }
}
