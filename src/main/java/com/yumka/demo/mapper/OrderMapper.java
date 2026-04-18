package com.yumka.demo.mapper;

import com.yumka.demo.dto.OrderResponse;
import com.yumka.demo.model.Order;

public class OrderMapper {
     public static OrderResponse toResponse(Order order) {

        return OrderResponse.builder()
                .orderId(order.getId())
                .total(order.getTotal().doubleValue())
                .status(order.getStatus())
                .build();
    }
}
