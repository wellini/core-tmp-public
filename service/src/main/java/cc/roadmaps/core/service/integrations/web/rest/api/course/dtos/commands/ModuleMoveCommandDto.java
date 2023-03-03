package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import cc.roadmaps.core.domain.services.course.operations.implementations.moduleMove.ModuleMoveCommand;
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
public class ModuleMoveCommandDto extends AbstractCommandDto implements ModuleMoveCommand {

    @Schema(required = true)
    @JsonProperty("moduleId")
    private UUID moduleId;

    @Schema(required = true)
    private int destinationOrderId;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("moduleId", this::getModuleId)
                    .strictlyRequire(Rules.notNull(), "Set module ID")
                    .and()
                .forProperty("destinationOrderId", this::getDestinationOrderId)
                    .strictlyRequire(Rules.notNull(), "Set destination order")
                    .require(Rules.min(0), "Destination order cannot be less than 0")
                    .and();
        // @formatter:on
    }
}
