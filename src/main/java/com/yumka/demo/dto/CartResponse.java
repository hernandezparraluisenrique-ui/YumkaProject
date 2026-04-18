package com.yumka.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartResponse {
     @NotNull
    @JsonProperty("cart_id")
    private UUID cartId;

    @JsonProperty("items")
    private List<CartItemDetail> items;

    @JsonProperty("total")
    private Double total;
}
