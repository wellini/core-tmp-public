package cc.roadmaps.core.domain.services.user;

import cc.roadmaps.core.domain.exceptions.EntityNotFoundDomainException;
import cc.roadmaps.core.domain.exceptions.UnauthorizedDomainException;
import cc.roadmaps.core.domain.model.user.User;
import cc.roadmaps.core.domain.model.user.UserRepository;
import cc.roadmaps.core.domain.services.CurrentUserIdProvider;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CurrentUserIdProvider currentUserIdProvider;
    private final UserRepository userRepository;

    @Override
    public Optional<User> findCurrentUser() {
        Optional<UUID> currentUserId = currentUserIdProvider.getCurrentUserId();
        return currentUserId.map(id -> userRepository.findUser(id).orElseThrow(EntityNotFoundDomainException.createExceptionSupplier(User.class, id)));
    }

    @Override
    public User getCurrentUser() {
        Optional<User> currentUser = findCurrentUser();
        return currentUser.orElseThrow(UnauthorizedDomainException::new);
    }

    @Override
    public User getOrCreate(String username, String fullname) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isPresent()) {
            return user.get();
        } else {
            User newUser = User.create(username, fullname);
            userRepository.save(newUser);
            return newUser;
        }
    }

    @Override
    public List<User> getStudentsInCourse(UUID courseId) {
        return userRepository.findStudentsInCourse(courseId);
    }
}
