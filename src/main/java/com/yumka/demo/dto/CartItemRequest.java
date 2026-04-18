package com.yumka.demo.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CartItemRequest {
    @NotNull
    @JsonProperty("user_id")
    private UUID userId;

    @NotNull
    @JsonProperty("product_id")
    private UUID productId;

    @NotNull
    @Min(1)
    @JsonProperty("quantity")
    private Integer quantity;
}
