package com.yumka.demo.service;

import com.yumka.demo.dto.PaymentRequest;
import com.yumka.demo.dto.PaymentResponse;
import com.yumka.demo.mapper.PaymentMapper;
import com.yumka.demo.model.Payment;
import com.yumka.demo.repository.OrderRepository;
import com.yumka.demo.repository.PaymentRepository;
import com.yumka.demo.model.Order;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
     private final PaymentRepository repository;
    private final OrderRepository orderRepository;

    @Override
    public PaymentResponse create(PaymentRequest request) {

        if (repository.existsByOrderId(request.getOrderId())) {
            throw new IllegalStateException("Order already paid");
        }

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Payment payment = PaymentMapper.toEntity(request, order);

        order.setStatus("PAID");
        orderRepository.save(order);

        return PaymentMapper.toResponse(repository.save(payment));
    }

    @Override
    public PaymentResponse findByOrder(UUID orderId) {
        return repository.findByOrderId(orderId)
                .map(PaymentMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
    }

    @Override
    public List<PaymentResponse> findByUser(UUID userId) {
        return repository.findByUser(userId)
                .stream().map(PaymentMapper::toResponse).toList();
    }

    @Override
    public List<PaymentResponse> findByProducer(UUID producerId) {
        return repository.findByProducer(producerId)
                .stream().map(PaymentMapper::toResponse).toList();
    }

    @Override
    public Double totalRevenue() {
        return repository.totalRevenue();
    }

    @Override
    public Double totalByProducer(UUID producerId) {
        return repository.totalByProducer(producerId);
    }
}
