package cc.roadmaps.extauth.model;

import cc.roadmaps.extauth.model.enums.AuthProviderType;

import java.util.Optional;
import java.util.UUID;

public interface AuthProviderRepository {

    Optional<AuthProvider> findByUserIdAndType(UUID userId, AuthProviderType type);

    void save(AuthProvider authProvider);
}
