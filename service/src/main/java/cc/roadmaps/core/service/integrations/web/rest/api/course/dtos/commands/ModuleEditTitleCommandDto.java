package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands;

import cc.roadmaps.core.domain.services.course.operations.implementations.moduleEditTitle.ModuleEditTitleCommand;
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
                    .addCheck(ModuleTitleCheck.getInstance())
                    .and();
        // @formatter:on
    }
}
