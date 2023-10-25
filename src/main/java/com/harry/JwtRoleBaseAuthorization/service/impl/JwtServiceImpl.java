package com.harry.JwtRoleBaseAuthorization.service.impl;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.harry.JwtRoleBaseAuthorization.service.IJwtService;

@Service
public class JwtServiceImpl implements IJwtService {

    @Override
    public String validateToken(String token) throws JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256("secret-key");
        // auto check for expired token
        DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        return decodedJWT.getClaim("username").asString();
    }
}
