package com.yumka.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yumka.demo.dto.OrderStatusHistoryResponse;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public interface  OrderStatusHistoryService {
        List<OrderStatusHistoryResponse> getHistory(UUID orderId);
}
