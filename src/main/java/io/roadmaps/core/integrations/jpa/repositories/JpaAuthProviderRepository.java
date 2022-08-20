package io.roadmaps.core.integrations.jpa.repositories;

import io.roadmaps.core.integrations.authproviders.model.AuthProvider;
import io.roadmaps.core.integrations.authproviders.model.AuthProviderRepository;
import io.roadmaps.core.integrations.authproviders.model.enums.AuthProviderType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = AuthProvider.class, idClass = Long.class)
public interface JpaAuthProviderRepository extends AuthProviderRepository {

    @Override
    @Query("SELECT ap FROM AuthProvider ap WHERE ap.userId = :userId AND ap.type = :type")
    @Transactional
    Optional<AuthProvider> findByUserIdAndType(@Param("userId") Long userId, @Param("type") AuthProviderType type);

    @Override
    @Transactional
    void save(AuthProvider authProvider);
}
