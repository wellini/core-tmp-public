package cc.roadmaps.core.domain.services.user;

import cc.roadmaps.core.domain.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> findCurrentUser();

    User getCurrentUser();

    User getOrCreate(String username, String fullname);

    List<User> getStudentsInCourse(UUID courseId);
}
