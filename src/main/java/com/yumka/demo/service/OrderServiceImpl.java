package com.yumka.demo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.yumka.demo.dto.OrderRequest;
import com.yumka.demo.dto.OrderResponse;
import com.yumka.demo.mapper.OrderMapper;
import com.yumka.demo.model.Address;
import com.yumka.demo.model.Cart;
import com.yumka.demo.model.CartItem;
import com.yumka.demo.model.OrderItem;
import com.yumka.demo.model.Product;
import com.yumka.demo.repository.CartItemRepository;
import com.yumka.demo.repository.CartRepository;
import com.yumka.demo.repository.OrderRepository;
import com.yumka.demo.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.yumka.demo.repository.AddressRepository;
import com.yumka.demo.repository.OrderStatusHistoryRepository;
imort java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
     private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;
    private final OrderStatusHistoryRepository historyRepository;

    @Override
    public OrderResponse create(OrderRequest request) {

        Cart cart = cartRepository.findByUserIdAndStatus(request.getUserId(), "CHECKOUT")
                .orElseThrow(() -> new IllegalStateException("Cart not ready"));

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        Address address = addressRepository.findById(request.getAddressId())
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        Order order = Order.builder()
                .user(cart.getUser())
                .address(address)
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        List<OrderItem> orderItems = items.stream().map(i -> {

            Product p = i.getProduct();

            if (p.getStock() < i.getQuantity()) {
                throw new IllegalStateException("Not enough stock");
            }

            p.setStock(p.getStock() - i.getQuantity());
            productRepository.save(p);

            return OrderItem.builder()
                    .order(order)
                    .product(p)
                    .quantity(i.getQuantity())
                    .price(p.getPrice())
                    .build();

        }).toList();

        BigDecimal total = orderItems.stream()
                .map(i -> i.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotal(total);
        order.setItems(orderItems);

        Order saved = orderRepository.save(order);

        historyRepository.save(OrderStatusHistory.builder()
                .order(saved)
                .status("PENDING")
                .changedAt(LocalDateTime.now())
                .build());

        cart.setStatus("CLOSED");
        cartRepository.save(cart);

        return OrderMapper.toResponse(saved);
    }

    @Override
    public List<OrderResponse> getByUser(UUID userId) {
        return orderRepository.findByUser(userId)
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
    }

    @Override
    public OrderResponse findById(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        return OrderMapper.toResponse(order);
    }
}
