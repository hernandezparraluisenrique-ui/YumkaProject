package com.yumka.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yumka.demo.dto.DeliveryRequest;
import com.yumka.demo.dto.DeliveryResponse;
import java.util.List;
import java.util.UUID;
 

@Service
@Transactional
public interface DeliveryService {
    DeliveryResponse create(DeliveryRequest request);

    DeliveryResponse updateStatus(UUID deliveryId, String status);

    DeliveryResponse findByOrder(UUID orderId);

    List<DeliveryResponse> findPending();
}
