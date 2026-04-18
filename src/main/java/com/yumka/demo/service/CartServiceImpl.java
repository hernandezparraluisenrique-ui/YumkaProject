package com.yumka.demo.service;

import java.time.LocalDateTime;

import com.yumka.demo.dto.CartItemRequest;
import com.yumka.demo.dto.CartResponse;
import com.yumka.demo.mapper.CartMapper;
import com.yumka.demo.model.Cart;
import com.yumka.demo.model.CartItem;
import com.yumka.demo.model.Product;
import com.yumka.demo.repository.CartItemRepository;
import com.yumka.demo.repository.CartRepository;
import com.yumka.demo.repository.ProductRepository;
import com.yumka.demo.repository.UserRefRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
     private final CartRepository cartRepository;
    private final CartItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final UserRefRepository userRepository;

    // 🔥 Obtener o crear carrito
    private Cart getOrCreateCart(UUID userId) {

        return cartRepository.findByUserIdAndStatus(userId, "ACTIVE")
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .user(userRepository.findById(userId).orElseThrow())
                                .status("ACTIVE")
                                .createdAt(LocalDateTime.now())
                                .build()
                ));
    }

    // 🔥 Agregar producto
    @Override
    public CartResponse addProduct(CartItemRequest request) {

        Cart cart = getOrCreateCart(request.getUserId());

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        if (!product.getIsActive()) {
            throw new IllegalStateException("Product is not available");
        }

        if (product.getStock() < request.getQuantity()) {
            throw new IllegalStateException("Not enough stock");
        }

        CartItem item = itemRepository.findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (item != null) {
            item.setQuantity(item.getQuantity() + request.getQuantity());
        } else {
            item = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
        }

        itemRepository.save(item);

        cart.setItems(itemRepository.findByCartId(cart.getId()));

        return CartMapper.toResponse(cart);
    }

    // 🔥 Obtener carrito
    @Override
    public CartResponse getCart(UUID userId) {

        Cart cart = getOrCreateCart(userId);

        cart.setItems(itemRepository.findByCartId(cart.getId()));

        return CartMapper.toResponse(cart);
    }

    // 🔥 Eliminar producto
    @Override
    public void removeProduct(UUID userId, UUID productId) {

        Cart cart = getOrCreateCart(userId);

        itemRepository.deleteByCartIdAndProductId(cart.getId(), productId);
    }

    // 🔥 Checkout
    @Override
    public CartResponse checkout(UUID userId) {

        Cart cart = getOrCreateCart(userId);

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        cart.setStatus("CHECKOUT");
        cartRepository.save(cart);

        return CartMapper.toResponse(cart);
    }
    
}
