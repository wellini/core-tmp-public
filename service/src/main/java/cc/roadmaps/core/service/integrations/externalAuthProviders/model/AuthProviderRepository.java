package cc.roadmaps.core.service.integrations.externalAuthProviders.model;

import cc.roadmaps.core.service.integrations.externalAuthProviders.model.enums.AuthProviderType;

import java.util.Optional;
import java.util.UUID;

public interface AuthProviderRepository {

    Optional<AuthProvider> findByUserIdAndType(UUID userId, AuthProviderType type);

    void save(AuthProvider authProvider);
}
