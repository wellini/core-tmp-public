package cc.roadmaps.extauth.services.specific.google;

import cc.roadmaps.extauth.exceptions.CannotGetAccessRefreshPairExternalAuthenticationException;
import cc.roadmaps.extauth.exceptions.CannotGetUserIdExternalAuthenticationException;
import cc.roadmaps.extauth.exceptions.CannotGetUserInfoExternalAuthenticationException;
import cc.roadmaps.extauth.model.AuthProvider;
import cc.roadmaps.extauth.model.enums.AuthProviderType;
import cc.roadmaps.extauth.bridges.UserServiceBridge;
import cc.roadmaps.extauth.services.authprovider.AuthProviderService;
import cc.roadmaps.extauth.services.authprovider.events.AuthProviderSavingEventImpl;
import cc.roadmaps.extauth.services.specific.Authentication;
import cc.roadmaps.extauth.services.specific.AuthenticationService;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class GoogleAuthenticationService implements AuthenticationService<GoogleAuthentication> {

    private static final String TOKEN_SERVER_ENCODED_URL = "https://oauth2.googleapis.com/token";

    private final GoogleAuthenticationServiceConfiguration configuration;
    private final UserServiceBridge userServiceBridge;
    private final AuthProviderService authProviderService;

    @Override
    public boolean canAuthenticate(Authentication<?> auth) {
        return auth.getProviderType() == AuthProviderType.GOOGLE;
    }

    @Override
    public AuthProvider authenticate(GoogleAuthentication auth) {
        AccessRefreshPair tokens = getGoogleTokens(auth);
        Userinfo userinfo = getUserinfo(tokens.getAccessToken());
        UUID userId = getUserId(userinfo);
        return authProviderService.createOrUpdate(
                AuthProviderSavingEventImpl.create(
                        userId,
                        AuthProviderType.GOOGLE,
                        userinfo.getId(),
                        tokens.getAccessToken(),
                        tokens.getRefreshToken()
                )
        );
    }

    private UUID getUserId(Userinfo userinfo) {
        try {
            return userServiceBridge.getUserIdOrCreateIfAbsent(userinfo.getEmail(), userinfo.getName());
        } catch (Exception e) {
            throw new CannotGetUserIdExternalAuthenticationException(e);
        }
    }

    private Userinfo getUserinfo(String googleAccessToken) {
        try {
            GoogleCredential credential = new GoogleCredential().setAccessToken(googleAccessToken);
            Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
                    .setApplicationName(configuration.getGoogleAppName())
                    .build();
            return oauth2.userinfo().get().execute();
        } catch (Exception e) {
            throw new CannotGetUserInfoExternalAuthenticationException(e);
        }
    }

    private AccessRefreshPair getGoogleTokens(GoogleAuthentication auth) {
        try {
            GoogleTokenResponse googleTokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance(),
                    TOKEN_SERVER_ENCODED_URL,
                    configuration.getGoogleClientId(),
                    configuration.getGoogleClientSecret(),
                    auth.getData().getCode(),
                    configuration.getGoogleRedirectUri()
            ).execute();
            return AccessRefreshPair.create(
                    googleTokenResponse.getAccessToken(),
                    googleTokenResponse.getRefreshToken()
            );
        } catch (Exception e) {
            throw new CannotGetAccessRefreshPairExternalAuthenticationException(e);
        }
    }

    @Getter
    @RequiredArgsConstructor(staticName = "create")
    private static class AccessRefreshPair {

        private final String accessToken;

        private final String refreshToken;
    }

    @Getter
    @Builder
    public static class GoogleAuthenticationServiceConfiguration {

        private final String googleClientId;

        private final String googleClientSecret;

        private final String googleRedirectUri;

        private final String googleAppName;
    }
}
