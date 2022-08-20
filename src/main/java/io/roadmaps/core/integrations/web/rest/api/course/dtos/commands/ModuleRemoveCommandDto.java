package io.roadmaps.core.integrations.web.rest.api.course.dtos.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.roadmaps.core.domain.services.course.operations.implementations.moduleRemove.ModuleRemoveCommand;
import io.roadmaps.core.hrid.HRID;
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
public class ModuleRemoveCommandDto extends AbstractCommandDto implements ModuleRemoveCommand {

    @Schema(required = true, type = "string")
    @JsonProperty("moduleId")
    private HRID moduleHRID;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("moduleId", this::getModuleHRID)
                    .strictlyRequire(Rules.notNull(), "Set module ID")
                    .and();
        // @formatter:on
    }
}
