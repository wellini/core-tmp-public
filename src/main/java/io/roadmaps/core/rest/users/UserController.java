package io.roadmaps.core.rest.users;

import io.roadmaps.core.exception.EntityNotFoundException;
import io.roadmaps.core.model.entity.User;
import io.roadmaps.core.repository.UserRepository;
import io.roadmaps.core.rest.users.converters.UserDtoConverter;
import io.roadmaps.core.rest.users.dto.GetMeResponseDto;
import io.roadmaps.core.rest.users.dto.GetUserResponseDto;
import io.roadmaps.core.security.AuthorizationService;
import io.roadmaps.core.security.annotations.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final UserDtoConverter converter;
    private final AuthorizationService authorizationService;

    @GetMapping("/api/users/me")
    public GetMeResponseDto getMe(@UserId UUID userId) {
        return converter.fromDomain(repository.getOne(userId), GetMeResponseDto.class);
    }

    @GetMapping("/api/users/{id}")
    public GetUserResponseDto getUser(@PathVariable UUID id) {
        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return converter.fromDomain(user, GetUserResponseDto.class);
    }
}
