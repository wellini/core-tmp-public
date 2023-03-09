package cc.roadmaps.core.service.integrations.web.rest.api.auth.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class JwtTokenService {

    private final static String ALGORITHM_NAME = "RSA";

    private final Algorithm algorithm;

    private final JWTVerifier jwtVerifier;

    private final int tokenLifeTimeInDays;

    public JwtTokenService(int tokenLifeTimeInDays, String publicKeyBase64, String privateKeyBase64) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.tokenLifeTimeInDays = tokenLifeTimeInDays;

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyBase64));
        X509EncodedKeySpec publcKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBase64));

        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(publcKeySpec);

        algorithm = Algorithm.RSA512(publicKey, privateKey);
        jwtVerifier = JWT.require(algorithm).build();
    }

    public String createToken(UUID userId) {
        try {
            Instant issuedAt = Instant.now();
            return JWT.create()
                    .withSubject(userId.toString())
                    .withIssuedAt(issuedAt)
                    .withExpiresAt(issuedAt.plus(tokenLifeTimeInDays, ChronoUnit.DAYS))
                    .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public DecodedJWT decode(String token) {
        return jwtVerifier.verify(token);
    }

    @SneakyThrows
    public static KeyPair generatePair() {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_NAME);
        keyPairGenerator.initialize(1024);
        return keyPairGenerator.generateKeyPair();
    }
}
