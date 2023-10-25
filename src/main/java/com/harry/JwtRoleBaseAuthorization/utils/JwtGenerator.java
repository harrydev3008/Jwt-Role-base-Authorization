package com.harry.JwtRoleBaseAuthorization.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.harry.JwtRoleBaseAuthorization.dto.response.UserResponseDTO;

public class JwtGenerator {
    private UserResponseDTO dto;

    public JwtGenerator(UserResponseDTO dto) {
        this.dto = dto;
    }

    public enum TokenType {
        ACCESS_TOKEN, REFRESH_TOKEN
    }

    public String generateToken(TokenType tokenType) {
        String token = "";

        Algorithm algorithm = Algorithm.HMAC256("secret-key");
        Instant now = Instant.now();

        switch (tokenType) {
            case ACCESS_TOKEN: {

                token = JWT.create().withClaim("id", dto.getId())
                        .withClaim("username", dto.getUsername())
                        .withIssuedAt(Date.from(now))
                        .withExpiresAt(Date.from(now.plus(1L, ChronoUnit.MINUTES)))
                        .sign(algorithm);

                break;
            }

            case REFRESH_TOKEN: {

                token = JWT.create().withClaim("id", dto.getId())
                        .withClaim("username", dto.getUsername())
                        .withIssuedAt(Date.from(now))
                        .withExpiresAt(Date.from(now.plus(1L, ChronoUnit.DAYS)))
                        .sign(algorithm);

                break;
            }
        }

        return token;
    }
}
