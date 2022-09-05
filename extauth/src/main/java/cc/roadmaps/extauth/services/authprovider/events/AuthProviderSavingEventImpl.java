package cc.roadmaps.extauth.services.authprovider.events;

import cc.roadmaps.extauth.model.enums.AuthProviderType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor(staticName = "create")
public class AuthProviderSavingEventImpl implements AuthProviderSavingEvent {

    private final UUID userId;

    private final AuthProviderType type;

    private final String externalId;

    private final String externalAccessToken;

    private final String externalRefreshToken;
}
