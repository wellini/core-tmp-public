package io.roadmaps.core.integrations.web.rest.course.dtos.commands;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class RemoveModuleCommandRequest {

    @Schema(required = true)
    private UUID moduleId;
}
