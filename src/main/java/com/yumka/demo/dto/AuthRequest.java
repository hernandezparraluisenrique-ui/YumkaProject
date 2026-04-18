package com.yumka.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data   
public class AuthRequest {
     @NotBlank(message = "Google ID is required")
    @JsonProperty("google_id")
    private String googleId;

    @NotBlank(message = "Email is required")
    @Email(message = "Email format is invalid")
    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("photo")
    private String photo;
    
}
