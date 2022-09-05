package cc.roadmaps.extauth.model;

import cc.roadmaps.extauth.model.enums.AuthProviderType;
import cc.roadmaps.extauth.model.external.External;
import cc.roadmaps.extauth.model.external.events.UpdateExternalEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class AuthProvider {

    private UUID id;

    private UUID userId;

    private AuthProviderType type;

    private External external;

    private AuthProvider(UUID userId, AuthProviderType type, String externalId, String externalAccessToken, String externalRefreshToken) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.userId = userId;
        this.external = External.create(
                externalId,
                externalAccessToken,
                externalRefreshToken
        );
    }

    public static AuthProvider create(UUID userId, AuthProviderType type, String externalId, String externalAccessToken, String externalRefreshToken) {
        return new AuthProvider(userId, type, externalId, externalAccessToken, externalRefreshToken);
    }

    public void updateExternal(UpdateExternalEvent event) {
        external.update(event);
    }
}
