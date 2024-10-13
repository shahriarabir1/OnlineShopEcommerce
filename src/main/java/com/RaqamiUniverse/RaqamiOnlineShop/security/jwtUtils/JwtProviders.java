package com.RaqamiUniverse.RaqamiOnlineShop.security.jwtUtils;

import com.RaqamiUniverse.RaqamiOnlineShop.security.user.UserInformation;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtProviders {
    private String secretKey="";

    public JwtProviders(){
        try {
            KeyGenerator keygen= KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk=keygen.generateKey();
            secretKey= Base64.getEncoder().encodeToString(sk.getEncoded());

        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public String generateToken(Authentication auth) {
        UserInformation userInformation=(UserInformation) auth.getPrincipal();
        List<String>roles=userInformation.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts.builder().subject(userInformation.getEmail()) // The subject is typically the username or email
                .claim("id", userInformation.getId())
                .claim("role",roles)// Authorities as a claim
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey(){
        byte[] encodedKey = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(encodedKey);
    }
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | io.jsonwebtoken.security.SecurityException | IllegalArgumentException e){
            throw new RuntimeException(e);
        }
    }
}
