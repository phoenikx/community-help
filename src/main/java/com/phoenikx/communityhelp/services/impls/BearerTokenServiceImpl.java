package com.phoenikx.communityhelp.services.impls;

import com.phoenikx.communityhelp.exceptions.InvalidRequestException;
import com.phoenikx.communityhelp.services.apis.BearerTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class BearerTokenServiceImpl implements BearerTokenService {
    private Key secretKey;
    private String issuer;
    private static final long TTL = TimeUnit.DAYS.toMillis(30);
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;

    public BearerTokenServiceImpl( @Value("${jwt.secret}") String secretKey,  @Value("${jwt.issuer}") String issuer) {
        this.issuer = issuer;
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }
    @Override
    public String generateToken(String phoneNumber, String userId) {
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDate = new Date(currentTimeMillis);

        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(currentDate)
                .setSubject(userId)
                .setIssuer(issuer)
                .setExpiration(new Date(currentTimeMillis + TTL))
                .signWith(secretKey, signatureAlgorithm);

        return builder.compact();
    }

    @Override
    public String verifyTokenAndGetSubject(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token).getBody();
        } catch (Exception ex) {
            throw new InvalidRequestException("Invalid token."); //signature not verified
        }
        return claims.getSubject();
    }
}
