package io.roadmaps.core.integrations.web.rest.api.course.dtos.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.roadmaps.core.domain.services.course.operations.implementations.leafMove.LeafMoveCommand;
import io.roadmaps.core.hrid.HRID;
import io.roadmaps.core.validation.Rules;
import io.roadmaps.core.validation.ValidationFlow;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LeafMoveCommandDto extends AbstractCommandDto implements LeafMoveCommand {

    @Schema(required = true, type = "string")
    @JsonProperty("leafId")
    private HRID leafHRID;

    @Schema(required = true, type = "string")
    @JsonProperty("destinationModuleId")
    private HRID destinationModuleHRID;

    @Schema(required = true)
    private int destinationOrderId;

    @Override
    protected <T> void configureSpecificValidations(ValidationFlow<T> validationFlow) {
        // @formatter:off
        validationFlow
                .forProperty("leafId", this::getLeafHRID)
                    .strictlyRequire(Rules.notNull(), "Set leaf ID")
                    .and()
                .forProperty("destinationModuleId", this::getDestinationModuleHRID)
                    .strictlyRequire(Rules.notNull(), "Set destination module")
                    .and()
                .forProperty("destinationOrderId", this::getDestinationOrderId)
                    .strictlyRequire(Rules.notNull(), "Set destination order")
                    .require(Rules.min(0), "Destination order cannot be less than 0")
                    .and();
        // @formatter:on
    }

    @Override
    public Long getDestinationModuleId() {
        return Optional.ofNullable(leafHRID).map(HRID::getRawId).orElse(null);
    }
}
