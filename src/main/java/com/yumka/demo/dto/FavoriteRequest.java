package com.yumka.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;


@Data
@Builder
public class FavoriteRequest {
    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private UUID userId;

    @NotNull(message = "Product ID is required")
    @JsonProperty("product_id")
    private UUID productId;

}
