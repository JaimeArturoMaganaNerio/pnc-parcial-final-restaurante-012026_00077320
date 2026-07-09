package com.uca.pncparcialfinalrestaurante.dto;

import lombok.Builder;
import lombok.Data;

// (DTO)
@Data
@Builder
public class JwtAuthResponse {
    private String accessToken;
    private String refreshToken;

    @Builder.Default
    private String tokenType = "Bearer";
}

