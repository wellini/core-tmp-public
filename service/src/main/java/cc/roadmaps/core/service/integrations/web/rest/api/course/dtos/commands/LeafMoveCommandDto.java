package cc.roadmaps.core.service.integrations.web.rest.api.course.dtos.commands;

import cc.roadmaps.core.domain.services.course.operations.implementations.leafMove.LeafMoveCommand;
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
public class LeafMoveCommandDto extends AbstractCommandDto implements LeafMoveCommand {

    @Schema(required = true)
    private UUID leafId;

    @Schema(required = true)
    private UUID destinationModuleId;

    @Schema(required = true)
    private int destinationOrderId;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("leafId", this::getLeafId)
                    .strictlyRequire(Rules.notNull(), "Set leaf ID")
                    .and()
                .forProperty("destinationModuleId", this::getDestinationModuleId)
                    .strictlyRequire(Rules.notNull(), "Set destination module")
                    .and()
                .forProperty("destinationOrderId", this::getDestinationOrderId)
                    .strictlyRequire(Rules.notNull(), "Set destination order")
                    .require(Rules.min(0), "Destination order cannot be less than 0")
                    .and();
        // @formatter:on
    }
}
