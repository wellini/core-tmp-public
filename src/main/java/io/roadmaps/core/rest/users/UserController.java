package io.roadmaps.core.rest.users;

import io.roadmaps.core.repository.UserRepository;
import io.roadmaps.core.rest.users.converters.UserDtoConverter;
import io.roadmaps.core.rest.users.dto.UserResponseDto;
import io.roadmaps.core.security.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final UserDtoConverter converter;
    private final AuthorizationService authorizationService;

    @GetMapping("/api/users/me")
    public UserResponseDto getMe() {
        return converter.fromDomain(repository.getOne(authorizationService.getCurrentUserId()));
    }
}
