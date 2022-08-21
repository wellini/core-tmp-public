package io.roadmaps.core.domain.services;

import java.util.Optional;
import java.util.UUID;

public interface CurrentUserIdProvider {

    /**
     * @return Optional.empty() if user not authorized, else his id
     */
    Optional<UUID> getCurrentUserId();
}
