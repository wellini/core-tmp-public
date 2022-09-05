package cc.roadmaps.core.service.config;

import cc.roadmaps.core.domain.services.user.UserService;
import cc.roadmaps.extauth.model.AuthProviderRepository;
import cc.roadmaps.extauth.services.AuthenticationManager;
import cc.roadmaps.extauth.services.AuthenticationManagerImpl;
import cc.roadmaps.extauth.services.authprovider.AuthProviderService;
import cc.roadmaps.extauth.services.authprovider.AuthProviderServiceImpl;
import cc.roadmaps.extauth.services.specific.Authentication;
import cc.roadmaps.extauth.services.specific.AuthenticationService;
import cc.roadmaps.extauth.services.specific.google.GoogleAuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ExtAuthConfig {

    @Value("${google.clientId}")
    private String googleClientId;
    @Value("${google.clientSecret}")
    private String googleClientSecret;
    @Value("${google.redirectUri}")
    private String googleRedirectUri;
    @Value("${google.appName}")
    private String googleAppName;

    @Bean
    public AuthProviderService getAuthProviderService(AuthProviderRepository authProviderRepository) {
        return new AuthProviderServiceImpl(authProviderRepository);
    }

    @Bean
    public GoogleAuthenticationService getGoogleAuthenticationService(UserService userService, AuthProviderService authProviderService) {
        GoogleAuthenticationService.GoogleAuthenticationServiceConfiguration configuration = GoogleAuthenticationService.GoogleAuthenticationServiceConfiguration.builder()
                .googleClientId(googleClientId)
                .googleClientSecret(googleClientSecret)
                .googleRedirectUri(googleRedirectUri)
                .googleAppName(googleAppName)
                .build();
        return new GoogleAuthenticationService(
                configuration,
                (String email, String name) -> userService.getOrCreate(email, name).getId(),
                authProviderService
        );
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(List<AuthenticationService<Authentication<?>>> authenticationServiceList) {
        return new AuthenticationManagerImpl(authenticationServiceList);
    }
}
