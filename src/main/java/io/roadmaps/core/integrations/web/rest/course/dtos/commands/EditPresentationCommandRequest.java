package io.roadmaps.core.integrations.web.rest.course.dtos.commands;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;
import io.roadmaps.core.domain.services.course.commands.EditPresentationServiceCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EditPresentationCommandRequest implements EditPresentationServiceCommand {

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private CourseCoverTheme coverTheme;
}
