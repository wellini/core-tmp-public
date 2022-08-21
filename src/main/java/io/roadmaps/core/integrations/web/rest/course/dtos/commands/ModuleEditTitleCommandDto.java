package io.roadmaps.core.integrations.web.rest.course.dtos.commands;

import io.roadmaps.core.domain.services.course.operations.implementations.moduleEditTitle.ModuleEditTitleCommand;
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
public class ModuleEditTitleCommandDto extends AbstractCommandDto implements ModuleEditTitleCommand {

    @Schema(required = true, type = "string")
    private UUID moduleId;

    @Schema(required = true)
    private String title;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("moduleId", this::getModuleId)
                    .strictlyRequire(Rules.notNull(), "Set module ID")
                    .and()
                .forProperty("title", this::getTitle)
                    .strictlyRequire(Rules.notBlank(), "Set title")
                    .require(Rules.maxLength(256, Rules.LengthComparisonMode.TRIMMED), "Length should be less than 256")
                    .and();
        // @formatter:on
    }
}
