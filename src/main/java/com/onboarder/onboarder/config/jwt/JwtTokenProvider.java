package com.onboarder.onboarder.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final long expirationTime;

    public JwtTokenProvider(@Value("${jwt.secret-key}") String secretKey,
                            @Value("${jwt.token.expiration-time}") long expirationTime) {

        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.expirationTime = expirationTime;
    }

    /**
     * JWT 토큰을 생성하는 메서드
     */
    public String createToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    /**
     * JWT 토큰에서 이메일을 추출하는 메서드
     */
    public String getUserEmail(String token) { // 임시로 만듦
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(stripBearer(token))
                .getPayload();

        return claims.getSubject();
    }

    /**
     * JWT 토큰의 유효성을 검사하는 메서드
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(stripBearer(token));
            return true;
        } catch (Exception e) {
            // 토큰이 유효하지 않거나 만료된 경우 예외가 발생
        }
        return false;
    }

    private String stripBearer(String t) {
        return (t != null && t.startsWith("Bearer ")) ? t.substring(7) : t;
    }
}
