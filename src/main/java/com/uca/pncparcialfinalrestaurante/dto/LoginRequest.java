package com.uca.pncparcialfinalrestaurante.dto;

import lombok.Builder;
import lombok.Data;

// (DTO)
@Data
@Builder
public class LoginRequest {
    private String username;
    private String password;
}

