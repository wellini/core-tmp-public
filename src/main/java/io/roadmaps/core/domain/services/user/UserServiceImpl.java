package io.roadmaps.core.domain.services.user;

import io.roadmaps.core.domain.common.id.Generator;
import io.roadmaps.core.domain.model.user.User;
import io.roadmaps.core.domain.model.user.UserRepository;
import io.roadmaps.core.domain.services.CurrentUserIdProvider;
import io.roadmaps.core.exception.EntityNotFoundException;
import io.roadmaps.core.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CurrentUserIdProvider currentUserIdProvider;
    private final UserRepository userRepository;
    private final Generator<Long> userIdIdSequenceGenerator;

    @Override
    public Optional<User> findCurrentUser() {
        Optional<Long> currentUserId = currentUserIdProvider.getCurrentUserId();
        return currentUserId.map(id -> userRepository.findUser(id).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public User getCurrentUser() {
        Optional<User> currentUser = findCurrentUser();
        return currentUser.orElseThrow(UnauthorizedException::new);
    }

    @Override
    public User getOrCreate(String username, String fullname) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()) {
            return user.get();
        } else {
            User newUser = User.create(userIdIdSequenceGenerator, username, fullname);
            userRepository.save(newUser);
            return newUser;
        }
    }

    @Override
    public List<User> getStudentsInCourse(Long courseId) {
        return userRepository.findStudentsInCourse(courseId);
    }
}
