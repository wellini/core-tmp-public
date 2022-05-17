package io.roadmaps.core.integrations.web.rest.leaf.dtos.commands;

import io.roadmaps.core.domain.model.leaf.commands.EditLeafTitleCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EditLeafTitleCommandRequest implements EditLeafTitleCommand  {

    @Schema(required = true)
    private String title;
}
