package com.yumka.demo.mapper;

import java.util.List;

import com.yumka.demo.dto.CartItemDetail;
import com.yumka.demo.dto.CartResponse;
import com.yumka.demo.model.Cart;


public class CartMapper {
    public static CartResponse toResponse(Cart cart) {

        if (cart == null) return null;

        List<CartItemDetail> items = cart.getItems()
                .stream()
                .map(i -> CartItemDetail.builder()
                        .productId(i.getProduct().getId())
                        .name(i.getProduct().getName())
                        .price(i.getProduct().getPrice().doubleValue())
                        .quantity(i.getQuantity())
                        .subtotal(i.getProduct().getPrice().doubleValue() * i.getQuantity())
                        .build())
                .toList();

        double total = items.stream().mapToDouble(CartItemDetail::getSubtotal).sum();

        return CartResponse.builder()
                .cartId(cart.getId())
                .items(items)
                .total(total)
                .build();
    }
}
