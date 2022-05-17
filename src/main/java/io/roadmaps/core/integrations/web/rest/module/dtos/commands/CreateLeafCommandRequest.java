package io.roadmaps.core.integrations.web.rest.module.dtos.commands;

import io.roadmaps.core.domain.model.leaf.enums.LeafType;
import io.roadmaps.core.domain.model.module.commands.LeafCreationCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data

public class CreateLeafCommandRequest implements LeafCreationCommand {

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private LeafType type;

    @Schema(required = true)
    private int orderId;

    @Schema(required = false)
    private String text;

    @Schema(required = false)
    private String link;
}
