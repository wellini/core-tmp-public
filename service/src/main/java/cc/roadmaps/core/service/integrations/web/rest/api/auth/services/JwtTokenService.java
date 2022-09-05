package cc.roadmaps.core.service.integrations.web.rest.api.auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.Period;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class JwtTokenService {

    private SecretKey secretKey;

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.tokenValidDays}")
    private int jwtTokenValidDays;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generate(UUID userId) {
        Instant instant = Instant.now();

        return Jwts.builder()
                .signWith(secretKey)
                .setId(UUID.randomUUID().toString())
                .setSubject(userId.toString())
                .setIssuedAt(Date.from(instant))
                .setExpiration(Date.from(instant.plus(Period.ofDays(jwtTokenValidDays))))
                .compact();
    }

    public Optional<Claims> parse(String token) {
        try {
            return Optional.ofNullable(Jwts.parserBuilder().setSigningKey(secretKey).build()
                    .parseClaimsJws(token)
                    .getBody());
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }

}
