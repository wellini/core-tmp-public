package io.roadmaps.core.service;

import io.roadmaps.core.model.entity.User;
import io.roadmaps.core.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;

    public UUID getOrCreateUser(String email, String name) {
        return userRepository.findByUsername(email).orElseGet(() -> {
            User user = new User();
            user.setUsername(email);
            user.setFullname(name);

            return userRepository.save(user);
        }).getId();
    }

}
