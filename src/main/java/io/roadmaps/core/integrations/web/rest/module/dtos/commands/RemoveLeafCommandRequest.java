package io.roadmaps.core.integrations.web.rest.module.dtos.commands;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class RemoveLeafCommandRequest {

    @Schema(required = true)
    private UUID leafId;
}
