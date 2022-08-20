package io.roadmaps.core.integrations.authproviders.model;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.integrations.authproviders.model.enums.AuthProviderType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthProvider {

    private Long id;

    private String providerId;

    private AuthProviderType type;

    private String token;

    private Long userId;

    private AuthProvider(Generator<Long> idGenerator, Long userId, AuthProviderType type, String providerId, String token) {
        this.id = idGenerator.generateNext();
        this.providerId = providerId;
        this.type = type;
        this.token = token;
        this.userId = userId;
    }

    public static AuthProvider create(Generator<Long> idGenerator, Long userId, AuthProviderType type, String providerId, String token) {
        return new AuthProvider(idGenerator, userId, type, providerId, token);
    }

    public void update(String providerId, String token) {
        this.providerId = providerId;
        this.token = token;
    }
}
