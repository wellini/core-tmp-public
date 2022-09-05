package cc.roadmaps.core.service.integrations.externalAuthProviders.model;

import cc.roadmaps.core.service.integrations.externalAuthProviders.model.enums.AuthProviderType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class AuthProvider {

    private UUID id;

    private String providerId;

    private AuthProviderType type;

    private String token;

    private UUID userId;

    private AuthProvider(UUID userId, AuthProviderType type, String providerId, String token) {
        this.id = UUID.randomUUID();
        this.providerId = providerId;
        this.type = type;
        this.token = token;
        this.userId = userId;
    }

    public static AuthProvider create(UUID userId, AuthProviderType type, String providerId, String token) {
        return new AuthProvider(userId, type, providerId, token);
    }

    public void update(String providerId, String token) {
        this.providerId = providerId;
        this.token = token;
    }
}
