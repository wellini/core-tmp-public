package cc.roadmaps.core.service.integrations.externalAuthProviders.services;

import cc.roadmaps.core.service.integrations.externalAuthProviders.model.enums.AuthProviderType;

import java.util.UUID;

public interface AuthProviderService {

    UUID saveAuthentication(UUID userId, AuthProviderType type, String providerId, String token);
}
