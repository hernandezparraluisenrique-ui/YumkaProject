package com.yumka.demo.service;

import com.yumka.demo.dto.OrderRequest;
import com.yumka.demo.dto.OrderResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public interface OrderService {
    OrderResponse create(OrderRequest request);

    List<OrderResponse> getByUser(UUID userId);

    OrderResponse findById(UUID orderId);
}
