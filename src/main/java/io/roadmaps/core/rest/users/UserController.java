package io.roadmaps.core.rest.users;

import io.roadmaps.core.repository.UserRepository;
import io.roadmaps.core.rest.users.converters.UserDtoConverter;
import io.roadmaps.core.rest.users.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.roadmaps.core.model.entity.User.DEFAULT_USER_ID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final UserDtoConverter converter;

    @GetMapping("/api/users/me")
    public UserResponseDto getMe() {
        return converter.fromDomain(repository.getOne(DEFAULT_USER_ID));
    }
}
