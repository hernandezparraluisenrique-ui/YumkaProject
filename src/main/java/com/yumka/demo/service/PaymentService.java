package com.yumka.demo.service;

import com.yumka.demo.dto.PaymentRequest;
import com.yumka.demo.dto.PaymentResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface PaymentService {
    
    PaymentResponse create(PaymentRequest request);

    PaymentResponse findByOrder(UUID orderId);

    List<PaymentResponse> findByUser(UUID userId);

    List<PaymentResponse> findByProducer(UUID producerId);

    Double totalRevenue();

    Double totalByProducer(UUID producerId);
}
