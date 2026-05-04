package com.yumka.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class GoogleLoginRequest {
    
    @JsonProperty("id_token")
    private String idToken;
}
