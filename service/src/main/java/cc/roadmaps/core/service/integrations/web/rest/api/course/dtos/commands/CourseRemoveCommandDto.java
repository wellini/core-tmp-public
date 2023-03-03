package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands;

import cc.roadmaps.core.domain.services.course.operations.implementations.courseRemove.CourseRemoveCommand;
import cc.roadmaps.validation.api.Rules;
import cc.roadmaps.validation.api.ValidationFlow;
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
public class CourseRemoveCommandDto extends AbstractCommandDto implements CourseRemoveCommand {

    @Schema(required = true)
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
