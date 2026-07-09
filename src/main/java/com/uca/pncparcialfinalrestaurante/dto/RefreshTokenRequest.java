package com.uca.pncparcialfinalrestaurante.dto;

import lombok.Builder;
import lombok.Data;

// (DTO)
@Data
@Builder
public class RefreshTokenRequest {
    private String refreshToken;
}

