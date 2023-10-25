package com.harry.JwtRoleBaseAuthorization.service;

import com.auth0.jwt.exceptions.JWTVerificationException;

public interface IJwtService {
    public String validateToken(String token) throws JWTVerificationException;
}