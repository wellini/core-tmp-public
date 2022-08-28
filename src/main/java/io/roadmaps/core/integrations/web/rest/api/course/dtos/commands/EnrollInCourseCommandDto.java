package io.roadmaps.core.integrations.web.rest.api.course.dtos.commands;

import io.roadmaps.core.domain.services.course.operations.implementations.enrollEncourse.EnrollInCourseCommand;
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
public class EnrollInCourseCommandDto extends AbstractCommandDto implements EnrollInCourseCommand {

    @Schema(required = true, type = "string")
    private UUID courseId;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("courseId", this::getCourseId)
                    .strictlyRequire(Rules.notNull(), "Set course ID")
                    .and();
        // @formatter:on
    }
}
