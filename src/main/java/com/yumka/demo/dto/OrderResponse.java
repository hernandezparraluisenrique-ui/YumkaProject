package com.yumka.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse  {
    
    @JsonProperty("order_id")
    private UUID orderId;

    @JsonProperty("total")
    private Double total;

    @JsonProperty("status")
    private String status;
}
