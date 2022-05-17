package io.roadmaps.core.integrations.web.rest.module.dtos.commands;

import io.roadmaps.core.domain.model.module.commands.EditModuleTitleCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EditModuleTitleCommandRequest implements EditModuleTitleCommand {

    @Schema(required = true)
    private String title;
}
