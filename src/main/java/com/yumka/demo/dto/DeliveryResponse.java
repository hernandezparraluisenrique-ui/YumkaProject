package com.yumka.demo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryResponse {
    @JsonProperty("delivery_id")
    private UUID id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("order_id")
    private UUID orderId;

    @JsonProperty("estimated_time")
    private LocalDateTime estimatedTime;
}
