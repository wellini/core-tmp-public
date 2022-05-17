package io.roadmaps.core.integrations.authproviders.services;

import io.roadmaps.core.integrations.authproviders.model.enums.AuthProviderType;

import java.util.UUID;

public interface AuthProviderService {

    UUID saveAuthentication(UUID userId, AuthProviderType type, String providerId, String token);
}
