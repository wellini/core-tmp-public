package io.roadmaps.core.integrations.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import io.roadmaps.core.domain.model.user.User;
import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.AuthException;
import io.roadmaps.core.integrations.authproviders.model.enums.AuthProviderType;
import io.roadmaps.core.integrations.authproviders.services.AuthProviderService;
import io.roadmaps.core.integrations.web.rest.api.auth.dto.response.LoginByGoogleProviderResponseDto;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationService {

    @NonNull
    private final JwtTokenService jwtTokenService;
    @NonNull
    private final UserService userService;
    @NonNull
    private final AuthProviderService authProviderService;

    @Value("${google.clientId}")
    private String googleClientId;
    @Value("${google.clientSecret}")
    private String googleClientSecret;
    @Value("${google.redirectUri}")
    private String googleRedirectUri;
    @Value("${google.appName}")
    private String googleAppName;

    public LoginByGoogleProviderResponseDto loginByGoogle(String code) {
        try {
            GoogleTokenResponse googleTokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance(),
                    "https://oauth2.googleapis.com/token",
                    googleClientId,
                    googleClientSecret,
                    code,
                    googleRedirectUri).execute();
            if (googleTokenResponse.getAccessToken() != null) {
                GoogleCredential credential = new GoogleCredential().setAccessToken(googleTokenResponse.getAccessToken());
                Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                        .setApplicationName(googleAppName)
                        .build();
                Userinfo userinfo = oauth2.userinfo().get().execute();

                User user = userService.getOrCreate(userinfo.getEmail(), userinfo.getName());

                authProviderService.saveAuthentication(user.getId(), AuthProviderType.google, userinfo.getId(), googleTokenResponse.getRefreshToken());

                return LoginByGoogleProviderResponseDto.builder()
                        .accessToken(jwtTokenService.generate(user.getId()))
                        .build();
            }
        } catch (Exception e) {
            log.error("Auth error", e);
        }
        throw new AuthException();
    }

}
