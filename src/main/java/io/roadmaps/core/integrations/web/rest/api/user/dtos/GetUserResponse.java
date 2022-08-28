package io.roadmaps.core.integrations.web.rest.api.user.dtos;

import io.roadmaps.core.domain.model.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GetUserResponse {

    @Schema(required = true)
    private UUID id;

    @Schema(required = true)
    private String fullname;

    @Schema(required = true)
    private String username;

    private GetUserResponse(User user) {
        id = user.getId();
        fullname = user.getFullname();
        username = user.getUsername();
    }

    public static GetUserResponse create(User user) {
        return new GetUserResponse(user);
    }
}
