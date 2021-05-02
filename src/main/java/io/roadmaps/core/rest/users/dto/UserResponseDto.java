package io.roadmaps.core.rest.users.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDto {

    private UUID id;

    private String fullname;

    private String username;
}
