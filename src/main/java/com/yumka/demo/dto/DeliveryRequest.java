package com.yumka.demo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import  jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;


@Data
@Builder
public class DeliveryRequest {
    @NotNull
    @JsonProperty("order_id")
    private UUID orderId;

    @JsonProperty("estimated_time")
    private LocalDateTime estimatedTime;
}
