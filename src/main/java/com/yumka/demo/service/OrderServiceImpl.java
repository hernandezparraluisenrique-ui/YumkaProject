package com.yumka.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.yumka.demo.dto.OrderResponse;
import com.yumka.demo.mapper.OrderMapper;
import com.yumka.demo.model.Address;
import com.yumka.demo.model.Cart;
import com.yumka.demo.model.CartItem;
import com.yumka.demo.model.Order;
import com.yumka.demo.model.OrderItem;
import com.yumka.demo.model.Product;
import com.yumka.demo.repository.AddressRepository;
import com.yumka.demo.repository.CartItemRepository;
import com.yumka.demo.repository.CartRepository;
import com.yumka.demo.repository.OrderRepository;
import com.yumka.demo.repository.ProductRepository;
import com.yumka.demo.model.Order;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;

    @Override
    public OrderResponse createOrderFromCart(UUID userId, UUID addressId) {

        Cart cart = cartRepository.findByUserIdAndStatus(userId, "CHECKOUT")
                .orElseThrow(() -> new IllegalStateException("Cart not ready"));

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        Order order = OrderMapper.toEntity(null, cart.getUser(), address);

        List<OrderItem> orderItems = items.stream().map(i -> {
            Product p = i.getProduct();

            if (p.getStock() < i.getQuantity())
                throw new IllegalStateException("Stock error");

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

        return OrderMapper.toResponse(orderRepository.save(order));
    }

    @Override
    public List<OrderResponse> findByUser(UUID userId) {
        return orderRepository.findByUser(userId)
                .stream().map(OrderMapper::toResponse).toList();
    }

    @Override
    public List<OrderResponse> findByProducer(UUID producerId) {
        return orderRepository.findByProducer(producerId)
                .stream().map(OrderMapper::toResponse).toList();
    }
}
