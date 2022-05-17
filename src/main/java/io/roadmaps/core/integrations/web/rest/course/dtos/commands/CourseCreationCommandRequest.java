package io.roadmaps.core.integrations.web.rest.course.dtos.commands;

import io.roadmaps.core.domain.model.course.commands.CourseCreationCommand;
import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CourseCreationCommandRequest implements CourseCreationCommand {

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private CourseCoverTheme coverTheme;

    @Schema(required = false)
    private String coverUrl;
}
