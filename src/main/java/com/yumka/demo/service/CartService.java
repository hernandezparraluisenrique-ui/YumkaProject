package com.yumka.demo.service;

import com.yumka.demo.dto.CartItemRequest;
import com.yumka.demo.dto.CartResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;


@Service
@Transactional
public interface CartService {
    CartResponse addProduct(CartItemRequest request);

    CartResponse getCart(UUID userId);

    void removeProduct(UUID userId, UUID productId);

    CartResponse checkout(UUID userId);
}
