package io.roadmaps.core.integrations.web.rest.course.dtos.commands;

import io.roadmaps.core.domain.model.course.enums.CourseCoverTheme;
import io.roadmaps.core.domain.services.course.operations.implementations.createCourse.CreateCourseCommand;
import io.roadmaps.core.validation.Rules;
import io.roadmaps.core.validation.ValidationFlow;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateCourseCommandDto extends AbstractCommandDto implements CreateCourseCommand {

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private CourseCoverTheme coverTheme;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("title", this::getTitle)
                    .strictlyRequire(Rules.notBlank(), "Set title")
                    .require(Rules.maxLength(256, Rules.LengthComparisonMode.TRIMMED), "Length should be less than 256")
                    .and()
                .forProperty("coverTheme", this::getCoverTheme)
                    .strictlyRequire(Rules.notNull(), "Set theme")
                    .and();
        // @formatter:on
    }
}
