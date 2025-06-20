package com.microservice.gateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;


@Component
public class JwtUtil {

    private final String secret = "5eb7fad0e8c4e8f4af0c3ec9c0a79a180fe41b4a0bc4d6ef3f5e168db1fb8a2c";
//    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Esto genera una clave segura automáticamente
//    String base64Key = Encoders.BASE64.encode(key.getEncoded());
    private final Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(
            "5eb7fad0e8c4e8f4af0c3ec9c0a79a180fe41b4a0bc4d6ef3f5e168db1fb8a2c"));


    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    public Claims validateToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
}
