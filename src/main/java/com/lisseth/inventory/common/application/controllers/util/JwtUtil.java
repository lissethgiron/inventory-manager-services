package com.lisseth.inventory.common.application.controllers.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.lisseth.inventory.common.domain.config.Exception;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "InventoryRest";
    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    private static final JWTVerifier verifier = JWT.require(algorithm).build();

    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(algorithm);
    }

    public static void checkAuth(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new Exception.UnauthorizedException("Unauthorized, missing token");
        }
        validateTokenAndGetUser(authHeader.substring(7));
    }

    protected static void validateTokenAndGetUser(String token) {
        try {
            DecodedJWT jwt = verifier.verify(token);
            jwt.getSubject();
        } catch (JWTVerificationException e) {
            throw new Exception.UnauthorizedException("Invalid or expired token");
        }
    }

}
