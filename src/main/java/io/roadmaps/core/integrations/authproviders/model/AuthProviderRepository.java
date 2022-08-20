package io.roadmaps.core.integrations.authproviders.model;

import io.roadmaps.core.integrations.authproviders.model.enums.AuthProviderType;

import java.util.Optional;
import java.util.UUID;

public interface AuthProviderRepository {

    Optional<AuthProvider> findByUserIdAndType(Long userId, AuthProviderType type);

    void save(AuthProvider authProvider);
}
