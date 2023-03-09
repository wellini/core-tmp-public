package cc.roadmaps.core.service.integrations.web.rest.api.user;

import cc.roadmaps.core.domain.services.user.UserService;
import cc.roadmaps.core.service.integrations.web.rest.api.user.dtos.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users/getMe")
    @PreAuthorize("isAuthenticated()")
    public GetUserResponse getMe() {
        return GetUserResponse.create(userService.getCurrentUser());
    }
}
