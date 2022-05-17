package io.roadmaps.core.domain.services.user;

import io.roadmaps.core.domain.model.user.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getCurrentUser();

    User getOrCreate(String username, String fullname);
}
