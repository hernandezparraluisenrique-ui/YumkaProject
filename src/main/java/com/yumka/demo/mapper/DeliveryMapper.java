package com.yumka.demo.mapper;

import com.yumka.demo.dto.DeliveryRequest;
import com.yumka.demo.dto.DeliveryResponse;
import com.yumka.demo.model.Delivery;
import com.yumka.demo.model.Order;




public class DeliveryMapper {
     public static DeliveryResponse toResponse(Delivery entity) {
        if (entity == null) return null;

        return DeliveryResponse.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .orderId(entity.getOrder() != null ? entity.getOrder().getId() : null)
                .estimatedTime(entity.getEstimatedTime())
                .build();
    }

    public static Delivery toEntity(DeliveryRequest dto, Order order) {
        if (dto == null) return null;

        return Delivery.builder()
                .order(order)
                .status("PENDING")
                .estimatedTime(dto.getEstimatedTime())
                .build();
    }

    public static void copyToEntity(DeliveryRequest dto, Delivery entity, Order order) {
        if (dto == null || entity == null) return;

        entity.setOrder(order);
        entity.setEstimatedTime(dto.getEstimatedTime());
    }
}
