package com.yumka.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.yumka.demo.dto.DeliveryRequest;
import com.yumka.demo.dto.DeliveryResponse;
import com.yumka.demo.mapper.DeliveryMapper;
import com.yumka.demo.model.Delivery;
import com.yumka.demo.repository.DeliveryRepository;
import com.yumka.demo.repository.OrderRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.yumka.demo.model.Order;
import com.yumka.demo.model.OrderStatusHistory;
import com.yumka.demo.repository.OrderStatusHistoryRepository;
import java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService{
    
    private final DeliveryRepository repository;
    private final OrderRepository orderRepository;
    private final OrderStatusHistoryRepository historyRepository;

    @Override
    public DeliveryResponse create(DeliveryRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Delivery delivery = DeliveryMapper.toEntity(request, order);

        return DeliveryMapper.toResponse(repository.save(delivery));
    }

    @Override
    public DeliveryResponse updateStatus(UUID deliveryId, String status) {

        Delivery delivery = repository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));

        delivery.setStatus(status);

        // 🔥 actualizar estado de orden también
        Order order = delivery.getOrder();
        order.setStatus(status);
        orderRepository.save(order);

        // 🔥 guardar historial
        historyRepository.save(
                OrderStatusHistory.builder()
                        .order(order)
                        .status(status)
                        .changedAt(LocalDateTime.now())
                        .build()
        );

        return DeliveryMapper.toResponse(repository.save(delivery));
    }

    @Override
    public DeliveryResponse findByOrder(UUID orderId) {
        return repository.findByOrderId(orderId)
                .map(DeliveryMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found"));
    }

    @Override
    public List<DeliveryResponse> findPending() {
        return repository.findPending()
                .stream().map(DeliveryMapper::toResponse).toList();
    }
}
