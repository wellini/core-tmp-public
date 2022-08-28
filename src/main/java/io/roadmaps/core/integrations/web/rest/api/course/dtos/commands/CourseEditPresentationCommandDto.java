package io.roadmaps.core.integrations.web.rest.api.course.dtos.commands;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;
import io.roadmaps.core.domain.services.course.operations.implementations.courseEditPresentation.CourseEditPresentationCommand;
import io.roadmaps.core.integrations.web.rest.api.course.checks.CourseTitleCheck;
import io.roadmaps.core.validation.Rules;
import io.roadmaps.core.validation.ValidationFlow;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CourseEditPresentationCommandDto extends AbstractCommandDto implements CourseEditPresentationCommand {

    @Schema(required = true, type = "string")
    private UUID courseId;

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private CourseCoverTheme coverTheme;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("courseId", this::getCourseId)
                    .strictlyRequire(Rules.notNull(), "Set course ID")
                    .and()
                .forProperty("title", this::getTitle)
                    .addCheck(CourseTitleCheck.getInstance())
                    .and()
                .forProperty("coverTheme", this::getCoverTheme)
                    .strictlyRequire(Rules.notNull(), "Set theme")
                    .and();
        // @formatter:on
    }
}
