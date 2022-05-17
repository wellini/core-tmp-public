package io.roadmaps.core.integrations.web.rest.course.dtos.commands;

import io.roadmaps.core.domain.model.course.commands.ModuleCreationCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateModuleCommandRequest implements ModuleCreationCommand {

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private int orderId;
}
