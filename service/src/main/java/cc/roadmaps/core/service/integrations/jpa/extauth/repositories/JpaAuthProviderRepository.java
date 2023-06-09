package cc.roadmaps.core.service.integrations.jpa.extauth.repositories;

import cc.roadmaps.extauth.model.AuthProvider;
import cc.roadmaps.extauth.model.AuthProviderRepository;
import cc.roadmaps.extauth.model.enums.AuthProviderType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = AuthProvider.class, idClass = UUID.class)
public interface JpaAuthProviderRepository extends AuthProviderRepository {

    @Override
    @Query("SELECT ap FROM AuthProvider ap WHERE ap.userId = :userId AND ap.type = :type")
    @Transactional
    Optional<AuthProvider> findByUserIdAndType(@Param("userId") UUID userId, @Param("type") AuthProviderType type);

    @Override
    @Transactional
    void save(AuthProvider authProvider);
}
