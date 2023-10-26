package com.harry.JwtRoleBaseAuthorization.service.impl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.harry.JwtRoleBaseAuthorization.model.ApplicationUser;
import com.harry.JwtRoleBaseAuthorization.model.enums.TokenType;
import com.harry.JwtRoleBaseAuthorization.service.IJwtService;

@Service
public class JwtServiceImpl implements IJwtService {

    @Value("${app.access-token-secret}")
    private String accessTokenSecretKey;

    @Value("${app.refresh-token-secret}")
    private String refreshTokenSecretKey;

    @Override
    public String validateToken(String token, boolean isRefreshToken) throws JWTVerificationException {
        Algorithm algorithm = generateAlgorithm(isRefreshToken ? TokenType.REFRESH_TOKEN : TokenType.ACCESS_TOKEN);
        // auto check for expired token
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        return decodedJWT.getClaim("username").asString();
    }

    @Override
    public String generateToken(ApplicationUser user, TokenType tokenType) {
        String token = "";

        Algorithm algorithm = generateAlgorithm(tokenType);
        Instant now = Instant.now();

        switch (tokenType) {
            case ACCESS_TOKEN: {

                token = JWT.create().withClaim("id", user.getId())
                        .withClaim("username", user.getUsername())
                        .withIssuedAt(Date.from(now))
                        .withExpiresAt(Date.from(now.plus(1L, ChronoUnit.MINUTES)))
                        .sign(algorithm);

                break;
            }

            case REFRESH_TOKEN: {

                token = JWT.create().withClaim("id", user.getId())
                        .withClaim("username", user.getUsername())
                        .withIssuedAt(Date.from(now))
                        .withExpiresAt(Date.from(now.plus(1L, ChronoUnit.DAYS)))
                        .sign(algorithm);

                break;
            }
        }

        return token;
    }

    @Override
    public Algorithm generateAlgorithm(TokenType tokenType) {
        Algorithm algorithm = null;
        switch (tokenType) {
            case ACCESS_TOKEN: {
                algorithm = Algorithm.HMAC256(accessTokenSecretKey);
            }

            case REFRESH_TOKEN: {
                algorithm = Algorithm.HMAC256(refreshTokenSecretKey);
            }
        }

        return algorithm;
    }

}