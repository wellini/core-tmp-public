package io.roadmaps.core.integrations.web.rest.api.course.dtos.commands;

import io.roadmaps.core.domain.services.course.operations.implementations.moduleCreate.ModuleCreateCommand;
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
public class ModuleCreateCommandDto extends AbstractCommandDto implements ModuleCreateCommand {

    @Schema(required = true, type = "string")
    private UUID courseId;

    @Schema(required = true)
    private String title;

    @Schema(required = true)
    private Integer orderId;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("courseId", this::getCourseId)
                    .strictlyRequire(Rules.notNull(), "Set course ID")
                    .and()
                .forProperty("title", this::getTitle)
                    .strictlyRequire(Rules.notBlank(), "Set title")
                    .require(Rules.maxLength(256, Rules.LengthComparisonMode.TRIMMED), "Length should be less than 256")
                    .and()
                .forProperty("orderId", this::getOrderId)
                    .strictlyRequire(Rules.notNull(), "Set order")
                    .and();
        // @formatter:on
    }
}
