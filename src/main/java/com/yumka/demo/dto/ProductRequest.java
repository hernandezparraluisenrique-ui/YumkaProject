package com.yumka.demo.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    @NotNull
    @JsonProperty("producer_id")
    private UUID producerId;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @NotNull
    @Positive
    @JsonProperty("price")
    private Double price;

    @NotNull
    @Min(0)
    @JsonProperty("stock")
    private Integer stock;

    @JsonProperty("category_ids")
    private List<UUID> categoryIds;
}
