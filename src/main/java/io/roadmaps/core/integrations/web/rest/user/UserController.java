package io.roadmaps.core.integrations.web.rest.user;

import io.roadmaps.core.domain.services.user.UserService;
import io.roadmaps.core.exception.EntityNotFoundException;
import io.roadmaps.core.integrations.web.rest.user.dtos.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users/getMe")
    public GetUserResponse getMe() {
        return GetUserResponse.create(userService.getCurrentUser().orElseThrow(() -> new EntityNotFoundException("User not found")));
    }
}
