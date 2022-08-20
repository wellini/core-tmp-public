package io.roadmaps.core.integrations.authproviders.services;

import io.roadmaps.core.integrations.authproviders.model.enums.AuthProviderType;

import java.util.UUID;

public interface AuthProviderService {

    Long saveAuthentication(Long userId, AuthProviderType type, String providerId, String token);
}
