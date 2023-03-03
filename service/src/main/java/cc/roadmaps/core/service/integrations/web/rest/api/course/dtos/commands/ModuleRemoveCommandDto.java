package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands;

import cc.roadmaps.validation.api.Rules;
import cc.roadmaps.validation.api.ValidationFlow;
import com.fasterxml.jackson.annotation.JsonProperty;
import cc.roadmaps.core.domain.services.course.operations.implementations.moduleRemove.ModuleRemoveCommand;
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
public class ModuleRemoveCommandDto extends AbstractCommandDto implements ModuleRemoveCommand {

    @Schema(required = true)
    @JsonProperty("moduleId")
    private UUID moduleId;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("moduleId", this::getModuleId)
                    .strictlyRequire(Rules.notNull(), "Set module ID")
                    .and();
        // @formatter:on
    }
}
