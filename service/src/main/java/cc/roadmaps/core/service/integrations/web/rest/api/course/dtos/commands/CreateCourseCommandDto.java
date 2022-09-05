package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands;

import cc.roadmaps.core.domain.model.course.enums.CourseCoverTheme;
import cc.roadmaps.core.domain.services.course.operations.implementations.createCourse.CreateCourseCommand;
import cc.roadmaps.core.service.integrations.web.rest.api.course.checks.CourseTitleCheck;
import cc.roadmaps.validation.api.Rules;
import cc.roadmaps.validation.api.ValidationFlow;
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
                    .addCheck(CourseTitleCheck.getInstance())
                    .and()
                .forProperty("coverTheme", this::getCoverTheme)
                    .strictlyRequire(Rules.notNull(), "Set theme")
                    .and();
        // @formatter:on
    }
}
