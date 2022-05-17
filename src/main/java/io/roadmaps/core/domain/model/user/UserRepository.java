package io.roadmaps.core.domain.model.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUser(UUID id);

    Optional<User> findUserByUsername(String username);

    void save(User user);
}
