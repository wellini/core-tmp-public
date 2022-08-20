package io.roadmaps.core.domain.model.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> findUser(Long id);

    Optional<User> findUserByUsername(String username);

    List<User> findStudentsInCourse(Long courseId);

    void save(User user);
}
