package io.roadmaps.core.integrations.jpa.repositories;

import io.roadmaps.core.domain.model.user.User;
import io.roadmaps.core.domain.model.user.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RepositoryDefinition(domainClass = User.class, idClass = UUID.class)
public interface JpaUserRepository extends UserRepository {

    @Override
    @Query("SELECT u FROM User u WHERE u.id = :id")
    @Transactional
    Optional<User> findUser(@Param("id") UUID id);

    @Override
    @Query("SELECT u FROM User u WHERE u.username = :username")
    @Transactional
    Optional<User> findUserByUsername(@Param("username") String username);

    @Override
    @Transactional
    void save(User user);
}
