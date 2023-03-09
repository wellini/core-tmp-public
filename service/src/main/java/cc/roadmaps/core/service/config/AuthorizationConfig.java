package cc.roadmaps.core.service.config;

import cc.roadmaps.core.service.integrations.web.rest.api.auth.filters.JwtFilter;
import cc.roadmaps.core.service.integrations.web.rest.api.auth.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.Base64;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AuthorizationConfig {

    private static final UUID DEFAULT_USER_ID = UUID.fromString("3950aca1-63d4-4573-a452-d00104e5e7fd");

    @Bean
    @SneakyThrows
    public JwtTokenService jwtTokenService(
            @Value("${authorization.tokenLifeTimeInDays:60}") int tokenLifeTimeInDays,
            @Value("${authorization.keyPair.public}") String publicKeyBase64,
            @Value("${authorization.keyPair.private}") String privateKeyBase64,
            @Value("${authorization.writeDefaultUserJWTOnStartup:false}") Boolean writeDefaultUserJWTOnStartupEnabled,
            @Value("${authorization.generateKeyPair:false}") Boolean generateKeyPair
    ) {
        if(generateKeyPair) {
            KeyPair keyPair = JwtTokenService.generatePair();
            String publicKey = new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()), StandardCharsets.UTF_8);
            String privateKey = new String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()), StandardCharsets.UTF_8);
            log.info("Public: {}", publicKey);
            log.info("Private: {}", privateKey);
        }
        JwtTokenService jwtTokenService = new JwtTokenService(tokenLifeTimeInDays, publicKeyBase64, privateKeyBase64);
        if(writeDefaultUserJWTOnStartupEnabled) {
            log.info("Default user JWT: {}", jwtTokenService.createToken(DEFAULT_USER_ID));
        }
        return jwtTokenService;
    }

    @Bean
    public JwtFilter jwtFilter(JwtTokenService jwtTokenService) {
        return new JwtFilter(jwtTokenService);
    }
}
