package com.yumka.demo.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CartItemDetail {
     @JsonProperty("product_id")
    private UUID productId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("subtotal")
    private Double subtotal;
}
