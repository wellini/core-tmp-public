package io.roadmaps.core.rest.users.dto.templates;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDtoTemplate {

    private UUID id;

    private String fullname;

    private String username;
}
