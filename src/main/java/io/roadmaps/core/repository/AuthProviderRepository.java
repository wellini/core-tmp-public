package io.roadmaps.core.repository;

import io.roadmaps.core.model.security.AuthProvider;
import io.roadmaps.core.model.security.enums.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthProviderRepository extends JpaRepository<AuthProvider, UUID> {

    Optional<AuthProvider> findByUserIdAndType(UUID userId, AuthProviderType type);

}
