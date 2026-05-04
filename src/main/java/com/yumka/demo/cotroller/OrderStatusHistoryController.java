package com.yumka.demo.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yumka.demo.dto.OrderStatusHistoryResponse;
import com.yumka.demo.service.OrderStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/orders/history")
@RequiredArgsConstructor
public class OrderStatusHistoryController {
    private final OrderStatusHistoryService service;

    @GetMapping("/{orderId}")
    public List<OrderStatusHistoryResponse> getHistory(@PathVariable UUID orderId) {
        return service.getHistory(orderId);
    }
}
