package io.roadmaps.core.integrations.web.rest.leaf.dtos.commands;

import io.roadmaps.core.domain.model.leaf.commands.UpdateTextCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateTextCommandRequest implements UpdateTextCommand {

    @Schema(required = true)
    private String text;
}
