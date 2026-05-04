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

    OrderResponse createOrderFromCart(UUID userId, UUID addressId);

    List<OrderResponse> findByUser(UUID userId);

    List<OrderResponse> findByProducer(UUID producerId);
}
