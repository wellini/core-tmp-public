package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands;

import cc.roadmaps.core.domain.services.course.operations.implementations.moduleCreate.ModuleCreateCommand;
import cc.roadmaps.core.service.integrations.web.rest.api.course.checks.ModuleTitleCheck;
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
public class ModuleCreateCommandDto extends AbstractCommandDto implements ModuleCreateCommand {

    @Schema(required = true)
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
                    .addCheck(ModuleTitleCheck.getInstance())
                    .and()
                .forProperty("orderId", this::getOrderId)
                    .strictlyRequire(Rules.notNull(), "Set order")
                    .and();
        // @formatter:on
    }
}
