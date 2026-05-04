package com.yumka.demo.service;

import com.yumka.demo.dto.OrderStatusHistoryResponse;
import com.yumka.demo.mapper.OrderStatusHistoryMapper;
import com.yumka.demo.repository.OrderStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class OrderStatusHistoryServiceImpl implements OrderStatusHistoryService {

     private final OrderStatusHistoryRepository repository;

    @Override
    public List<OrderStatusHistoryResponse> getHistory(UUID orderId) {
        return repository.findByOrderId(orderId)
                .stream()
                .map(OrderStatusHistoryMapper::toResponse)
                .toList();
    }
    }

