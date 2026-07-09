package com.uca.pncparcialfinalrestaurante.service;

import com.uca.pncparcialfinalrestaurante.dto.JwtAuthResponse;
import com.uca.pncparcialfinalrestaurante.dto.LoginRequest;
import com.uca.pncparcialfinalrestaurante.dto.RefreshTokenRequest;

// (Service)
public interface AuthService {
    JwtAuthResponse login(LoginRequest loginRequest);

    JwtAuthResponse refresh(RefreshTokenRequest refreshTokenRequest);
}

