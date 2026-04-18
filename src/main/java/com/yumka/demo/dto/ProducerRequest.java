package com.yumka.demo.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProducerRequest {
    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private UUID userId;

    @NotBlank(message = "Business name is required")
    @JsonProperty("business_name")
    private String businessName;

    @Size(max = 500)
    @JsonProperty("description")
    private String description;

    @NotBlank(message = "Location is required")
    @JsonProperty("location")
    private String location;
}
