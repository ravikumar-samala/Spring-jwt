package com.example.microservice.rest.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {


    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private String issuer ="test";
    private long expiryDuration = 10000000;
    String ISSUER = "iss";
    String SUBJECT = "sub";
    String AUDIENCE = "aud";
    String EXPIRATION = "exp";
    String NOT_BEFORE = "nbf";
    String ISSUED_AT = "iat";
    String ID = "jti";



    public String createJWT(String id,  String subject) {

        // The JWT signature algorithm used to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //  sign JWT with our ApiKey secret
      //  byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
       // Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //  set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, key);

        if (expiryDuration >= 0) {
            long expMillis = nowMillis + expiryDuration;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    // Sample method to validate and read the JWT
    public Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as
        // expected)
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims;
    }

    public String extractUserName(String jwt){
        Claims claims = parseJWT(jwt);
        return claims.getSubject();
    }

    private Boolean isTokenExpired(String jwt) {

        Claims claims = parseJWT(jwt);
        return claims.getExpiration().before(new Date());

    }

    public Boolean validateToken(String jwt, UserDetails userDetails) {
        final String username = extractUserName(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }
}
