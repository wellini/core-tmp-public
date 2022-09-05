package cc.roadmaps.extauth.services.specific.google;

import cc.roadmaps.extauth.model.enums.AuthProviderType;
import cc.roadmaps.extauth.services.specific.Authentication;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class GoogleAuthentication implements Authentication<GoogleAuthentication.GoogleAuthenticationData> {

    @Getter
    private final GoogleAuthenticationData data;

    private GoogleAuthentication(String code) {
        this.data = new GoogleAuthenticationData(code);
    }

    public static GoogleAuthentication create(String code) {
        return new GoogleAuthentication(code);
    }

    @Override
    public AuthProviderType getProviderType() {
        return AuthProviderType.GOOGLE;
    }

    @Getter
    @RequiredArgsConstructor
    public static class GoogleAuthenticationData {

        private final String code;
    }
}
