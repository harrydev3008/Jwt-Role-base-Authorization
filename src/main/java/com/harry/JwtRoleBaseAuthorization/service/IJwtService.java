package com.harry.JwtRoleBaseAuthorization.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.harry.JwtRoleBaseAuthorization.model.ApplicationUser;
import com.harry.JwtRoleBaseAuthorization.model.enums.TokenType;

public interface IJwtService {
    String validateToken(String token, boolean isRefreshToken) throws JWTVerificationException;

    String generateToken(ApplicationUser user, TokenType tokenType);

    Algorithm generateAlgorithm(TokenType tokenType);
}